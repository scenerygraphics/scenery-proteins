package graphics.scenery.proteins

import cleargl.GLVector
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

//TODO Besides, this function should do it. Then you need to find out, whether there are different chains
//TODO in a protein, well of course there are but is it possible to have more than one c aplha trace?
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


    fun flatRibbon(): Spline {
        val aminoList =  ArrayList<Group>(groups.size)
        /*
        TODO I assume, something is wrong with the getAtom() or hasAtom() functions in biojava because
        TODO when I use for loops to iterate through the groups then I get a lot more cA atoms compared to
        TODO the named functions. My idea would be now to make two lists of atom coordinates, one for the
        TODO CA atoms and one for the O atoms. The open question there is: will the lists be off the same size?
        TODO Otherwise the algorithm from Carlson et. al would not work. Then I'd need to check the distances
        TODO between the CA atoms from one list and the O atoms from the other list and if the distance is below
        TODO a certain threshold then assign them to each other. But this is not an ideal solution. Yet still, in
        TODO a perfect world, not PDB would contain different numbers of CA and O atoms because each peptide bond
        TODO contains exactly one of each.
         */
        val caList = ArrayList<Atom>(groups.size)
        val oList = ArrayList<Atom>(groups.size)
        chains.forEach{ chain ->
            chain.atomGroups.forEach { group ->
                if(group.atoms.filter { it.name == "CA" }.isNotEmpty()) {
                    aminoList.add(group)
                }
                group.atoms.forEach {atom ->
                    when {
                        (atom.name ==  "CA") -> caList.add(atom)
                        (atom.name == "O") -> oList.add(atom)
                    }
                }
            }
        }
        val guidePoints = calculateGuidePoints(caList, oList)
        val finalSpline = ArrayList<GLVector>(guidePoints.size*100)
        val pts1 = ArrayList<GLVector>(guidePoints.size)
        val pts2 = ArrayList<GLVector>(guidePoints.size)
        guidePoints.forEach{
            val ribWith = if(it.offset > 0f){widthAlpha} else {widthBeta}
            val halfwidth = 0.5f*(widthCoil + it.widthFactor*(ribWith-widthCoil))
            pts1.add(it.finalPoint.plus(it.dVec.times(halfwidth)))
            pts2.add(it.finalPoint.plus(it.dVec.times(-halfwidth)))
        }
        val spline1 = UniformBSpline(pts1).splinePoints()
        val spline2 = UniformBSpline(pts2).splinePoints()
        spline1.forEachIndexed{ i, _ ->
            finalSpline.add(spline1[i].plus(spline2[i]).times(0.5f))
        }
        return UniformBSpline(pts1)
    }

    data class GuidePoint(val finalPoint: GLVector, val cVec: GLVector, val dVec: GLVector,
                          val offset: Float = 0f, var widthFactor: Float = 0f,
                          val prevCa: Atom, val nextCa: Atom)

    private fun calculateGuidePoints(caList: ArrayList<Atom>, oList: ArrayList<Atom>): ArrayList<GuidePoint> {
        val guidePointsWithoutDummy = ArrayList<GuidePoint>(caList.size-1)
        val guidePoints = ArrayList<GuidePoint>(caList.size + 4 -1)
        val maxOffSet = 1.5f
        caList.dropLast(1).forEachIndexed { i, _ ->
            val ca1 = caList[i]
            val ca2 = caList[i + 1]
            val ca1Vec = GLVector(ca1.x.toFloat(), ca1.y.toFloat(), ca1.z.toFloat())
            val ca2Vec = GLVector(ca2.x.toFloat(), ca2.y.toFloat(), ca2.z.toFloat())
            val aVec = ca1Vec.minus(ca2Vec)
            val o = oList[i]
            val bVec = GLVector(o.x.toFloat(), o.y.toFloat(), o.z.toFloat())

            val finalPoint = ca1Vec.plus(ca2Vec).times(0.5f)
            //See Carlson et. al
            val cVec = aVec.cross(bVec).normalized
            val dVec = aVec.cross(cVec).normalized
            val widthFactor: Float
            val offset: Float
            when {
                (i >= 1 && i < caList.size - 3) -> {
                    val ca0 = caList[i - 1]
                    val ca3 = caList[i + 2]
                    val ca0Vec = GLVector(ca0.x.toFloat(), ca0.y.toFloat(), ca0.z.toFloat())
                    val ca3Vec = GLVector(ca3.x.toFloat(), ca3.y.toFloat(), ca3.z.toFloat())
                    val ca0Ca3 = ca0Vec.minus(ca3Vec)
                    val ca0Ca3Distance = ca0Ca3.length2()
                    when {
                        (ca0Ca3Distance > 7f) -> {
                            widthFactor = min(1.5f, 7f - ca0Ca3Distance) / 1.5f
                            offset = min(1.5f, ca0Ca3Distance - 9f) / 1.5f
                            val ca0Ca3Midpoint = ca0Ca3.times(0.5f)
                            val offsetVec = finalPoint.minus(ca0Ca3Midpoint).normalized.times(offset * maxOffSet)
                            finalPoint.plus(offsetVec)
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
                    caList[i], caList[i+1]))
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
        val caBegin = caList[0]
        val caBeginVec = GLVector(caBegin.x.toFloat(), caBegin.y.toFloat(), caBegin.z.toFloat())
        val dummyVecBeg = GLVector(randomFromRange(caBeginVec.x()-1f, caBeginVec.x()+1f),
                                randomFromRange(caBeginVec.y()-1f, caBeginVec.y()+1f),
                                randomFromRange(caBeginVec.z()-1f, caBeginVec.z()+1f))
        guidePoints.add(GuidePoint(dummyVecBeg, guidePointsWithoutDummy[0].cVec, guidePointsWithoutDummy[0].dVec,
                guidePointsWithoutDummy[0].offset, guidePointsWithoutDummy[0].widthFactor, caList[0], caList[0]))
        guidePoints.add(GuidePoint(caBeginVec, guidePointsWithoutDummy[0].cVec, guidePointsWithoutDummy[0].dVec,
                guidePointsWithoutDummy[0].offset, guidePointsWithoutDummy[0].widthFactor, caList[0], caList[0]))
        //add all guide points from the previous calculation
        guidePoints.addAll(guidePointsWithoutDummy)
        //add dummy points at the end
        val caEnd = caList.last()
        val caEndVec = GLVector(caEnd.x.toFloat(), caEnd.y.toFloat(), caEnd.z.toFloat())
        guidePoints.add(GuidePoint(caEndVec,
                guidePointsWithoutDummy.last().cVec, guidePointsWithoutDummy.last().dVec,
                guidePointsWithoutDummy.last().offset, guidePointsWithoutDummy.last().widthFactor,
                caList.last(), caList.last()))
        val dummyVecEnd = GLVector(randomFromRange(caEndVec.x()-1f, caEndVec.x()+1f),
                                randomFromRange((caEndVec.y()-1f), caEndVec.y()+1f),
                                randomFromRange(caEndVec.z()-1f, caEndVec.z()+1f))
        guidePoints.add(GuidePoint(dummyVecEnd,
                guidePointsWithoutDummy.last().cVec, guidePointsWithoutDummy.last().dVec,
                guidePointsWithoutDummy.last().offset, guidePointsWithoutDummy.last().widthFactor,
                caList.last(), caList.last()))

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
            val ca1Vec = GLVector(ca1.x.toFloat(), ca1.y.toFloat(), ca1.z.toFloat())
            val ca2Vec = GLVector(ca2.x.toFloat(), ca2.y.toFloat(), ca2.z.toFloat())
            if(ca1Vec.minus(ca2Vec).length2() > maxCaCaDistance) {
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
                if (guidePoints[i].dVec.times(guidePoints[i - 1].dVec) < 0f) {
                    guidePoints[i].dVec.times(-1f)
                }
            }
        }
        return guidePoints
    }

}