package graphics.scenery.proteins

import cleargl.GLVector
import graphics.scenery.Mesh
import graphics.scenery.Protein
import org.biojava.nbio.structure.AminoAcid
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.HetatomImpl

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
                          val prevResidue: Group, val nextResidue: Group)

    fun calculateGuidePoints(): ArrayList<GuidePoint> {
        val guidePoints = ArrayList<GuidePoint>(groups.size)
        val allAminoAcids = chains.filter{it.isProtein}.flatMap{ chain ->
            chain.atomGroups.filter{it.isAminoAcid && it.hasAtom("CA") }}
        val allAminoAcidsDistinct = distinctChains(allAminoAcids)
        //TODO look at the Ribbons Class to see exactly what they are doing with
        //TODO the distinct chains (do they draw them each or only the longest chain
        //TODO or what not? Then create the guidePoints according to the code and finally
        //TODO see what you can do to assign secondary structures effectively. :)
        return guidePoints
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