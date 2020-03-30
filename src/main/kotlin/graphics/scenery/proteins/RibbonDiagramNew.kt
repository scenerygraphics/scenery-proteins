package graphics.scenery.proteins

import cleargl.GLVector
import graphics.scenery.Mesh
import graphics.scenery.Protein
import graphics.scenery.numerics.Random.Companion.randomFromRange
import org.biojava.nbio.structure.AminoAcid
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.HetatomImpl
import java.lang.Float.min
import kotlin.random.Random

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
class RibbonDiagramNew(val protein: Protein): Mesh("RibbonDiagram") {

    val structure = protein.structure
    val chains = structure.chains
    val groups = chains.flatMap { it.atomGroups }

    data class GuidePoint(val finalPoint: GLVector, val cVec: GLVector, val dVec: GLVector,
                          val offset: Float = 0f, val widthFactor: Float = 0f,
                          val prevResidue: Group?, val nextResidue: Group?)

    fun calculateGuidePoints(aminoList: List<Group>): ArrayList<GuidePoint> {
        val aminoList = aminoList.filter { it.isAminoAcid && it.hasAtom("CA") && it.hasAtom("O")}
        val guidePoints = ArrayList<GuidePoint>(aminoList.size+4-1)
        val maxOffSet = 1.5f
        aminoList.dropLast(2).forEachIndexed{ i, _ ->
            val ca1 = aminoList[i].getAtom("CA")
            val ca2 = aminoList[i+1].getAtom("CA")
            val ca1ca2 = GLVector((ca1.x.toFloat()-ca2.x.toFloat()),
                    (ca1.y.toFloat()-ca2.y.toFloat()),
                    (ca1.z.toFloat()-ca2.z.toFloat()))
            val o = aminoList[i].getAtom("O")
            val oxygenVec = GLVector(o.x.toFloat(), o.y.toFloat(), o.z.toFloat())

            val finalPoint = ca1ca2.times(0.5f)
            //See Carlson et. al
            val cVec = ca1ca2.cross(oxygenVec).normalized
            val dVec = cVec.cross(cVec).normalized
            val widthFactor: Float
            val offset: Float
            /*
             "Based on Ca(i-1) to Ca(i+2) distance, we may adjust ribbon width
             and/or guidepoint position. Ribbon widens in areas of high OR low
             curvature (alpha/beta, respectively). This is only a preliminary
             estimate -- it's applied only for 3+ residues in a row. (checked below)

             For high curvature ONLY (alpha), we offset the guidepoint
             outwards to make the spline track the chain (esp. helix);
             this is done for each and does not require 3+ in a row.
             Offset vector goes from the midpoint of Ca(i-1) to Ca(i+2)
             thru the current guide point
             (which is the midpoint of Ca(i) and Ca(i+1)).

             CA-CA DIST  WIDTH FACTOR    OFFSET FACTOR   NOTE
                  ==========  ============    =============   ====
                  5.0         1               1               ~limit for curled-up protein
                  5.5         1               1               } linear interpolation
                  7.0         0               0               } from 1.0 to 0.0
                  9.0         0               0               } linear interpolation
                  10.5        1               0               } from 1.0 to 0.0
                  11.0        1               0               ~limit for extended protein",
                  from: KiNG
             */
            if(i >= 1 && i < aminoList.size-3) {
                val ca0 = aminoList[i-1].getAtom("CA")
                val ca3 = aminoList[i+2].getAtom("CA")
                val ca0Ca3 = GLVector((ca0.x.toFloat()- ca3.x.toFloat()),
                        (ca0.y.toFloat()- ca3.y.toFloat()),
                        (ca0.z.toFloat()- ca3.z.toFloat()))
                val ca0Ca3Distance = ca0Ca3.length2()
                when {
                    (ca0Ca3Distance> 7f) -> {
                        widthFactor = min(1.5f, 7f - ca0Ca3Distance) / 1.5f
                        offset = min(1.5f, ca0Ca3Distance - 9f) / 1.5f
                        val ca0Ca3Midpoint = ca0Ca3.times(0.5f)
                        val offsetVec = finalPoint.minus(ca0Ca3Midpoint).normalized.times(offset*maxOffSet)
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
                guidePoints.add(i+2, GuidePoint(finalPoint, cVec, dVec, offset, widthFactor,
                aminoList[i-1], aminoList[i+1]))
            }
            else {
                widthFactor = 0f
                offset = 0f
                guidePoints.add(i+2, GuidePoint(finalPoint, cVec, dVec, offset, widthFactor,
                        null, null))
            }

        }
        //TODO see why they are using [i+2] for the guide point and [i+1] for its next residue,
        //TODO this doesn't make much sense to me. Also they are making two dummy vectors at the
        //TODO beginning and the end, which would only make sense if they want to assign the
        //TODO correct width factor to each residue, but this comes with a price: the control points
        //TODO of the first residue are not calculated exactly. But on a second thought this is probably
        //TODO the way to go since it would be impossible to change the offset afterwards (the first
        //TODO residue has no predecessor and the last one no successor). But the problem remains, why
        //TODO they are using the direct predecessor of a residue and name it "nextRes". This is the puzzle
        //TODO to solve.
        //TODO Besides, this function should do it. Then you need to find out, whether there are different chains
        //TODO in a protein, well of course there are but is it possible to have more than one c aplha trace?
        //TODO Afterwards, assign each Residue a secondary Structure (ss) if there are more than three in a row and
        //TODO the offset is bigger than 0, draw the ss with a Bspline with the control points from predecessor
        //TODO of the beginning of the ss to the successor of the end of ss. This should do it and the only
        //TODO open question remains: How to elegantly draw the different geometries?
        return guidePoints
        /*
        val allAminoAcids = chains.filter{it.isProtein}.flatMap{ chain ->
            chain.atomGroups.filter{it.isAminoAcid && it.hasAtom("CA") }}
        val allAminoAcidsDistinct = distinctChains(allAminoAcids)
         */
    }

    private fun distinctChains(aminoAcids: List<Group>): ArrayList<ArrayList<Group>> {
        val chains = ArrayList<ArrayList<Group>>(10)
        val chain = ArrayList<Group>(aminoAcids.size)
        aminoAcids.dropLast(1).forEachIndexed { i, aminoAcid ->
            chain.add(aminoAcid)
            val maxCaCaDistance = 5.0f
            val ca1 = aminoAcids[i].getAtom("CA")
            val ca2 = aminoAcids[i+1].getAtom("CA")
            val ca1ca2Distance = GLVector((ca1.x-ca2.x).toFloat(), (ca1.y-ca2.y).toFloat(),
                    (ca1.z-ca2.z).toFloat()).length2()
            if(ca1ca2Distance > maxCaCaDistance) {
                chains.addAll(distinctChains(aminoAcids.drop(i)))
                return chains
            }
        }
        chains.add(chain)
        return chains
    }

}