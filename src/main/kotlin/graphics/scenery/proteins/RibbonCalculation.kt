package graphics.scenery.proteins

import org.joml.*
import graphics.scenery.Mesh
import graphics.scenery.Protein
import graphics.scenery.numerics.Random.Companion.randomFromRange
import org.biojava.nbio.structure.Atom
import org.biojava.nbio.structure.Group
import java.lang.Float.min

/**
 * A Ribbon Diagram for Proteins. Ribbon models are currently the most common representations of proteins.
 * They draw a Spline approximately along the Backbone and highlight secondary structures like alpha helices
 * with a wider curve along the helix, beta sheets are represented with arrows pointing from the N-terminal
 * to the C-terminal. If you want to know more about the Ribbon diagrams, see the Wikipedia article:
 * https://en.wikipedia.org/wiki/Ribbon_diagram
 * In case you want to dig deeper here is an article of Jane D. Richardson, who developed the Ribbon:
 * https://www.ncbi.nlm.nih.gov/pmc/articles/PMC3535681/ (Studying and Polishing the PDB's Macromolecules)
 *
 * In the code below, we use a Uniform B-Spline to draw the C-alpha trace of the protein. The controlpoints
 * are created via the algorithm of Carlson and Bugg (Algorithm for ribbon models of proteins, Carson et. al)
 * The algorithm roughly works like this:
 * Take a list of coordinates of C-alpha-atoms (Ca) and the coordinates
 * of the Oxygen-atoms (O) of their carbonyl groups. Form the vectors:
 *
 * A(i) = Ca(i)-Ca(i+1)  (i is the index)
 * B(i) = O(i) - Ca(i)
 * C = A X B  (X is the cross product)
 * D = A X C
 *
 * After normalizing C and D, we now form the midpoints between two consecutive C-alpha atoms:
 * P = [Ca(i)-Ca(i+1)]/2
 *
 * Then we scale D by the desired Ribbon width. Finally, we calculate the guide points:
 * P1 = P - D
 * P2 = P + D
 *
 * For the implementation we have consulted KiNG, a visualization software by David C. Richardson et al:
 * https://www.ncbi.nlm.nih.gov/pmc/articles/PMC2788294/
 * (KING (Kinemage, Next Generation): A versatile interactive molecular and scientific visualization program,
 * by Richardson et al.)
 */


//TODO Afterwards, assign each Residue a secondary Structure (ss) if there are more than three in a row and
//TODO the offset is bigger than 0, draw the ss with a Bspline with the control points from predecessor
//TODO of the beginning of the ss to the successor of the end of ss. This should do it and the only
//TODO open question remains: How to elegantly draw the different geometries?
/*
        val allAminoAcids = chains.filter{it.isProtein}.flatMap{ chain ->
            chain.atomGroups.filter{it.isAminoAcid && it.hasAtom("CA") }}
        val allAminoAcidsDistinct = distinctChains(allAminoAcids)
         */
class RibbonCalculation(val protein: Protein): Mesh("RibbonDiagram") {

    private val structure = protein.structure
    private val chains = structure.chains
    private val groups = chains.flatMap { it.atomGroups }
    private val widthAlpha = 2.0f
    private val widthBeta = 2.2f
    private val widthCoil = 1.0f

    private fun Atom.getVector(): Vector3f {
        return Vector3f(this.x.toFloat(), this.y.toFloat(), this.z.toFloat())
    }

    fun flatRibbon(): Spline {
        val aminoList =  ArrayList<Group>(groups.size)
        chains.forEach{ chain ->
            chain.atomGroups.forEach { group ->
                if(group.hasAminoAtoms()) {
                    aminoList.add(group)
                }
            }
        }
        val guidePoints = calculateGuidePoints(aminoList)
        aminoList.forEachIndexed { index, it ->
            val ca = it.getAtom("CA").getVector()
            println("$ca and ${guidePoints[index+2]}")
        }

        val finalSpline = ArrayList<Vector3f>(guidePoints.size*100)
        val pts1 = ArrayList<Vector3f>(guidePoints.size)
        val pts2 = ArrayList<Vector3f>(guidePoints.size)
        guidePoints.forEach{
            val ribWith = if(it.offset > 0f){widthAlpha} else {widthBeta}
            val halfwidth = 0.5f*(widthCoil + it.widthFactor*(ribWith-widthCoil))
            val dVecPlus = Vector3f()
            it.finalPoint.add(it.dVec.mul(halfwidth, dVecPlus), dVecPlus)
            val dVecMinus = Vector3f()
            it.finalPoint.add(it.dVec.mul(-halfwidth, dVecMinus), dVecMinus)
            pts1.add(dVecPlus)
            pts2.add(dVecMinus)
        }
        val spline1 = UniformBSpline(pts1).splinePoints()
        val spline2 = UniformBSpline(pts2).splinePoints()
        spline1.forEachIndexed{ i, _ ->
            val splinePoint = Vector3f()
            spline1[i].add(spline2[i], splinePoint)
            splinePoint.mul(0.5f)
            finalSpline.add(splinePoint)
        }
        return DummySpline(finalSpline)
    }

    data class GuidePoint(val finalPoint: Vector3f, val cVec: Vector3f, val dVec: Vector3f,
                          val offset: Float = 0f, var widthFactor: Float = 0f,
                          val prevResidue: Group?, val nextResidue: Group?)

    private fun calculateGuidePoints(aminoList: List<Group>): ArrayList<GuidePoint> {
        val guidePointsWithoutDummy = ArrayList<GuidePoint>(aminoList.size-1)
        val guidePoints = ArrayList<GuidePoint>(aminoList.size + 4 -1)
        val maxOffSet = 1.5f
        //TODO make it windowed
        aminoList.dropLast(1).forEachIndexed { i, _ ->
            val ca1 = aminoList[i].getAtom("CA")
            val ca2 = aminoList[i + 1].getAtom("CA")
            val ca1Vec = ca1.getVector()
            val ca2Vec = ca2.getVector()
            val aVec = Vector3f()
            ca1Vec.sub(ca2Vec, aVec)
            val o = aminoList[i].getAtom("O")
            val bVec = o.getVector()

            val finalPoint = Vector3f()
            ca1Vec.add(ca2Vec, finalPoint)
            finalPoint.mul(0.5f)
            //See Carlson et. al
            val cVec = Vector3f()
            aVec.cross(bVec, cVec)
            cVec.normalize()
            val dVec = Vector3f()
            cVec.cross(aVec, dVec)
            cVec.normalize()
            val widthFactor: Float
            val offset: Float
            when {
                (i >= 1 && i < aminoList.size - 3) -> {
                    val ca0 = aminoList[i - 1].getAtom("CA").getVector()
                    val ca3 = aminoList[i + 2].getAtom("CA").getVector()
                    val ca0Ca3 = Vector3f()
                    ca0.sub(ca3, ca0Ca3)
                    val ca0Ca3Distance = ca0.distance(ca3)
                    when {
                        (ca0Ca3Distance > 7f) -> {
                            widthFactor = min(1.5f, 7f - ca0Ca3Distance) / 1.5f
                            offset = min(1.5f, ca0Ca3Distance - 9f) / 1.5f
                            val ca0Ca3Midpoint = Vector3f()
                            ca0Ca3.mul(0.5f, ca0Ca3Midpoint)
                            val offsetVec = Vector3f()
                            finalPoint.sub(ca0Ca3Midpoint, offsetVec)
                            offsetVec.normalize().mul(offset * maxOffSet)
                            finalPoint.add(offsetVec)
                        }
                        (ca0Ca3Distance < 9f) -> {
                            widthFactor = min(1.5f, ca0Ca3Distance - 9f) / 1.5f
                            offset = 0f
                        }
                        else -> {
                            widthFactor = 0f
                            offset = 0f
                        }
                    }
                }
                else -> {
                    widthFactor = 0f
                    offset = 0f
                }
            }
            guidePointsWithoutDummy.add(i, GuidePoint(finalPoint, cVec, dVec, offset, widthFactor,
                    aminoList[i], aminoList[i+1]))
        }

        //only assign widthFactor if there are three guide points with one in a row
        guidePointsWithoutDummy.dropLast(2).forEachIndexed { j, _ ->
            if(guidePointsWithoutDummy[j].widthFactor != 0f) {
                if (guidePointsWithoutDummy[j+1].widthFactor == 0f || guidePointsWithoutDummy[j+2].widthFactor == 0f) {
                    guidePointsWithoutDummy[j].widthFactor = 0f
                }
            }
        }
        //if there is a width factor is still assigned at the beginning, also assign it to the first point
        if(guidePointsWithoutDummy[1].widthFactor != 0f && guidePointsWithoutDummy[2].widthFactor != 0f &&
                guidePointsWithoutDummy[3].widthFactor != 0f) {
            guidePointsWithoutDummy[0].widthFactor = guidePointsWithoutDummy[1].widthFactor
        }
        //if there is a width factor is still assigned at the and, also assign it to the last point
        if(guidePointsWithoutDummy.dropLast(1).last().widthFactor != 0f &&
                guidePointsWithoutDummy.dropLast(2).last().widthFactor != 0f &&
                guidePointsWithoutDummy.dropLast(3).last().widthFactor != 0f) {
            guidePointsWithoutDummy.last().widthFactor = guidePointsWithoutDummy.dropLast(1).last().widthFactor
        }

        //dummy points at the beginning
        fun Vector3f.randomFromVector(): Vector3f {
            return Vector3f( randomFromRange(this.x()-1f, this.x()+1f),
                        randomFromRange(this.y()-1f, this.y()+1f),
                        randomFromRange(this.z()-1f, this.z()+1f)) }
        val caBegin = aminoList[0].getAtom("CA").getVector()
        val dummyVecBeg = caBegin.randomFromVector()
        guidePoints.add(GuidePoint(dummyVecBeg, guidePointsWithoutDummy[0].cVec, guidePointsWithoutDummy[0].dVec,
                guidePointsWithoutDummy[0].offset, guidePointsWithoutDummy[0].widthFactor, aminoList[0], aminoList[0]))
        guidePoints.add(GuidePoint(caBegin, guidePointsWithoutDummy[0].cVec, guidePointsWithoutDummy[0].dVec,
                guidePointsWithoutDummy[0].offset, guidePointsWithoutDummy[0].widthFactor, aminoList[0], aminoList[0]))
        //add all guide points from the previous calculation
        guidePoints.addAll(guidePointsWithoutDummy)
        //add dummy points at the end
        val caEnd = aminoList.last().getAtom("CA").getVector()
        guidePoints.add(GuidePoint(caEnd,
                guidePointsWithoutDummy.last().cVec, guidePointsWithoutDummy.last().dVec,
                guidePointsWithoutDummy.last().offset, guidePointsWithoutDummy.last().widthFactor,
                aminoList.last(), aminoList.last()))
        val dummyVecEnd = caEnd.randomFromVector()
        guidePoints.add(GuidePoint(dummyVecEnd,
                guidePointsWithoutDummy.last().cVec, guidePointsWithoutDummy.last().dVec,
                guidePointsWithoutDummy.last().offset, guidePointsWithoutDummy.last().widthFactor,
                aminoList.last(), aminoList.last()))

        return untwistRibbon(guidePoints)
    }

    private fun distinctChains(aminoAcids: List<Group>): ArrayList<ArrayList<Group>> {
        val chains = ArrayList<ArrayList<Group>>(10)
        val chain = ArrayList<Group>(aminoAcids.size)
        aminoAcids.dropLast(1).forEachIndexed { i, aminoAcid ->
            chain.add(aminoAcid)
            val maxCaCaDistance = 5.0f
            val ca1 = aminoAcids[i].getAtom("CA")
            val ca2 = aminoAcids[i+1].getAtom("CA")
            val ca1Vec = Vector3f(ca1.x.toFloat(), ca1.y.toFloat(), ca1.z.toFloat())
            val ca2Vec = Vector3f(ca2.x.toFloat(), ca2.y.toFloat(), ca2.z.toFloat())
            if(ca1Vec.distance(ca2Vec) > maxCaCaDistance) {
                chains.addAll(distinctChains(aminoAcids.drop(i)))
                return chains
            }
        }
        chains.add(chain)
        return chains
    }

    private fun untwistRibbon(guidePoints: ArrayList<GuidePoint>): ArrayList<GuidePoint> {
        guidePoints.forEachIndexed{ i, _ ->
            if(i > 0) {
                if (guidePoints[i].dVec.dot(guidePoints[i - 1].dVec) < 0f) {
                    guidePoints[i].dVec.mul(-1f)
                }
            }
        }
        return guidePoints
    }

}