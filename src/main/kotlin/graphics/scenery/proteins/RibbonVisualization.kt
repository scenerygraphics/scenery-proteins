package graphics.scenery.proteins

import graphics.scenery.Mesh
import org.joml.*
import graphics.scenery.Protein
import graphics.scenery.numerics.Random.Companion.randomFromRange
import org.biojava.nbio.structure.Atom
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.secstruc.SecStrucCalc
import org.biojava.nbio.structure.secstruc.SecStrucElement
import org.biojava.nbio.structure.secstruc.SecStrucTools
import org.biojava.nbio.structure.secstruc.SecStrucType

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
 *
 * @author Justin Buerger <burger@mpi-cbg.de>
 * @param [protein] the polypeptide you wish to visualize, stored in the class Protein
 */

class RibbonVisualization(val protein: Protein) {

    /*
     *[structure] the structure of the protein stored in the class Structure of BioJava
     *[chains] all the chains of the protein (C-alpha trace and sidechains)
     *[groups] atomGroups within the chains
     *[widthAlpha] specifies how wide outside the C-alpha trace the controlpoints
     * of the alpha helices will be
     *[widthBeta] specifies how wide outside the C-alpha trace the controlpoints
     * of the beta sheets will be
     *[widthCoil] specifies how wide outside the C-alpha trace the controlpoints
     * of the coil will be. The value 1 corresponds to the point laying approximately on
     * the trace.
     *[aminoList] List of all the amino acids in the protein
     *[sectionVerticesCount] Specifies how fine grained the geometry along the backbone
     * will be. Please note that the calculation could take much longer if this parameter is too
     * big, especially for large proteins.
     */
    private val structure = protein.structure
    private val chains = structure.chains
    private val groups = chains.flatMap { it.atomGroups }
    private val widthAlpha = 2.0f
    private val widthBeta = 2.2f
    private val widthCoil = 1.0f
    private val chainList =  ArrayList<List<Group>>(groups.size)
    private val sectionVerticesCount = 10
    private val secStrucs = dssp()

    /**
     * Returns the final Ribbon Diagram
     */
    fun ribbonCurve(): Mesh {
        val ribbon = Mesh("Mesh of the Ribbon")
        chains.forEach{ chain ->
            if(chain.isProtein) {
                val aminoList = ArrayList<Group>(chain.atomGroups.size)
                chain.atomGroups.forEach { group ->
                    if (group.hasAminoAtoms()) {
                        aminoList.add(group)
                    }
                }
                chainList.add(aminoList)
            }
        }
        chainList.forEach { aminoList ->
            val calculation = RibbonCalculation(protein, secStrucs)
            val guidePointList = calculation.calculateGuidePoints(aminoList)
            val spline = ribbonSpline(guidePointList)
            val chainCurve = Curve(spline) { baseShape(guidePointList, spline.splinePoints().size) }
            ribbon.addChild(chainCurve)
        }
        return ribbon
    }

    /**
     * The baseShape for the Spline, the coil is represented with an octagon,
     * the helices with rectangles, and the sheets with arrows.
     */
    fun baseShape(guidePointList: ArrayList<GuidePoint>, size: Int): List<List<Vector3f>> {

        val baseShapeList = ArrayList<ArrayList<Vector3f>>(size)
        val rectangle = ArrayList<Vector3f>(4)
        rectangle.add(Vector3f(1f, 0.1f, 0f))
        rectangle.add(Vector3f(-1f, 0.1f, 0f))
        rectangle.add(Vector3f(-1f, -0.1f, 0f))
        rectangle.add(Vector3f(1f, -0.1f, 0f))

        val octagon = ArrayList<Vector3f>(8)
        val sin45 = kotlin.math.sqrt(2f) / 40f
        octagon.add(Vector3f(0.05f, 0f, 0f))
        octagon.add(Vector3f(sin45, sin45, 0f))
        octagon.add(Vector3f(0f, 0.05f, 0f))
        octagon.add(Vector3f(-sin45, sin45, 0f))
        octagon.add(Vector3f(-0.05f, 0f, 0f))
        octagon.add(Vector3f(-sin45, -sin45, 0f))
        octagon.add(Vector3f(0f, -0.05f, 0f))
        octagon.add(Vector3f(sin45, -sin45, 0f))

        val reversedRectangle = ArrayList<Vector3f>(4)
        reversedRectangle.add(Vector3f(0.1f, 1f, 0f))
        reversedRectangle.add(Vector3f(-0.1f, 1f, 0f))
        reversedRectangle.add(Vector3f(-0.1f, -1f, 0f))
        reversedRectangle.add(Vector3f(0.1f, -1f, 0f))
        var guidePointsOffset = 1
        while(guidePointsOffset < guidePointList.lastIndex-1) {
            val guide = guidePointList[guidePointsOffset]
            val count = guide.count
            guidePointsOffset++
            when {
                (guide.type == SecStrucType.helix4) -> {
                    guidePointsOffset += count
                    for (j in 0 .. count) {
                        for (i in 0 .. sectionVerticesCount) {
                            baseShapeList.add(rectangle)
                        }
                    }
                }
                (guide.type.isBetaStrand) -> {
                    val sheetSize = (count+1)*(sectionVerticesCount+1)
                    guidePointsOffset += count
                    val seventyeightPercent = (sheetSize*0.78).toInt()
                    for (j in 0 until seventyeightPercent) {
                        baseShapeList.add(reversedRectangle)
                    }
                    val twentytwoPercent = sheetSize-seventyeightPercent
                    for(j in twentytwoPercent downTo 1) {
                        val y = 1.5f*j/twentytwoPercent
                        val x = 0.1f
                        val arrowHeadList = ArrayList<Vector3f>(4)
                        arrowHeadList.add(Vector3f(x, y, 0f))
                        arrowHeadList.add(Vector3f(-x, y, 0f))
                        arrowHeadList.add(Vector3f(-x, -y, 0f))
                        arrowHeadList.add(Vector3f(x, -y, 0f))
                        baseShapeList.add(arrowHeadList)
                    }
                }
                else -> {
                    guidePointsOffset += count
                    for (j in 0 .. count) {
                        for (i in 0 .. sectionVerticesCount) {
                            baseShapeList.add(octagon)
                        }
                    }
                }
            }
        }
        return baseShapeList
    }


    /**
     * Returns the secondary structures of a protein, calculated with the dssp algorithm. For additional
     * information about the algorithm see https://swift.cmbi.umcn.nl/gv/dssp/
     */
    private fun dssp(): List<SecStrucElement> {
        //see: https://github.com/biojava/biojava-tutorial/blob/master/structure/secstruc.md
        val ssc = SecStrucCalc()
        ssc.calculate(structure, true)
        return SecStrucTools.getSecStrucElements(structure)
    }

    /**
     * data class containing two B-Splines, which will be melted into one.
     */
    data class SplineSkeleton(
            val splineSkeleton1: ArrayList<Vector3f>,
            val splineSkeleton2: ArrayList<Vector3f>)
    /**
     * Calculates the splineSkeleton out of the GuidePoints
     */
    private fun splineSkeleton(guidePoints: ArrayList<GuidePoint>): SplineSkeleton {
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
        return SplineSkeleton(pts1, pts2)
    }

    /**
     * Melts the two B-Splines of the SplineSkeleton into one Dummy-Spline
     * (a spline which list of controlPoints is equal to its guidePoints)
     */
    private fun ribbonSpline(guidePoints: ArrayList<GuidePoint>): Spline {
        val finalSpline = ArrayList<Vector3f>(guidePoints.size * sectionVerticesCount)
        val skeleton = splineSkeleton(guidePoints)
        val spline1 = UniformBSpline(skeleton.splineSkeleton1, sectionVerticesCount).splinePoints()
        val spline2 = UniformBSpline(skeleton.splineSkeleton2, sectionVerticesCount).splinePoints()
        spline1.forEachIndexed { i, _ ->
            val splinePoint = Vector3f()
            spline1[i].add(spline2[i], splinePoint)
            splinePoint.mul(0.5f)
            finalSpline.add(splinePoint)
        }
        return DummySpline(finalSpline)
    }
}
