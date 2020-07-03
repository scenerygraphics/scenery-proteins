package graphics.scenery.proteins

import org.joml.*
import graphics.scenery.Mesh
import graphics.scenery.Protein
import graphics.scenery.numerics.Random.Companion.randomFromRange
import org.biojava.nbio.structure.Atom
import org.biojava.nbio.structure.AtomPositionMap
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.secstruc.SecStrucCalc
import org.biojava.nbio.structure.secstruc.SecStrucElement
import org.biojava.nbio.structure.secstruc.SecStrucTools
import org.biojava.nbio.structure.secstruc.SecStrucType
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
 *
 * @author Justin Buerger <burger@mpi-cbg.de>
 * @param [protein] the polypeptide you wish to visualize, stored in the class Protein
 */

class RibbonCalculation(val protein: Protein) {

    /**
     *@param [structure] the structure of the protein stored in the class Structure of BioJava
     *@param [chains] all the chains of the protein (C-alpha trace and sidechains)
     *@param [groups] atomGroups within the chains
     *@param [widthAlpha] specifies how wide outside the C-alpha trace the controlpoints
     * of the alpha helices will be
     *@param [widthBeta] specifies how wide outside the C-alpha trace the controlpoints
     * of the beta sheets will be
     *@param [widthCoil] specifies how wide outside the C-alpha trace the controlpoints
     * of the coil will be. The value 1 corresponds to the point laying approximately on
     * the trace.
     *@param [aminoList] List of all the amino acids in the protein
     *@param [sectionVerticesCount] Specifies how fine grained the geometry along the backbone
     * will be. Please note that the calculation could take much longer if this parameter is too
     * big, especially for large proteins.
     */
    private val structure = protein.structure
    private val chains = structure.chains
    private val groups = chains.flatMap { it.atomGroups }
    private val widthAlpha = 2.0f
    private val widthBeta = 2.2f
    private val widthCoil = 1.0f
    private val aminoList =  ArrayList<Group>(groups.size)
    private val sectionVerticesCount = 10

    /**
     * Returns the final Ribbon Diagram
     */
    fun ribbonCurve(): Curve {
        chains.forEach{ chain ->
            chain.atomGroups.forEach { group ->
                if(group.hasAminoAtoms()) {
                    aminoList.add(group)
                }
            }
        }
        val guidePointList = calculateGuidePoints(aminoList)
        val spline = ribbonSpline(guidePointList)

        /**
         * The baseShape for the Spline, the coil is represented with an octagon,
         * the helices with rectangles, and the sheets with arrows.
         */
        fun baseShape(): List<List<Vector3f>> {
            val baseShapeList = ArrayList<List<Vector3f>>(spline.splinePoints().size)
            val splinePoints = spline.splinePoints()
            var verticesIndex = 0
            //The different groups are summarized into sections
            //TODO only summarize beta strands!
            data class Section(val type: SecStrucType, val pointsOfSection: ArrayList<Vector3f>)
            val sectionList = ArrayList<Section>(guidePointList.size)
            guidePointList.drop(1).dropLast(1).windowed(2, 1) { pointToTypeWindow ->
                when {
                    (pointToTypeWindow[0].type == SecStrucType.helix4) -> {
                        var helixIndex = 0
                        val helixList = ArrayList<Vector3f>(sectionVerticesCount)
                        splinePoints.drop(verticesIndex).takeWhile {
                            verticesIndex++
                            helixIndex++
                            helixList.add(it)
                            (helixIndex <= sectionVerticesCount && verticesIndex < splinePoints.size)
                        }
                        sectionList.add(Section(pointToTypeWindow[0].type, helixList))
                    }
                    (pointToTypeWindow[0].type.isBetaStrand) -> {
                        var sheetIndex = 0
                        val sheetList = ArrayList<Vector3f>(sectionVerticesCount)
                        splinePoints.drop(verticesIndex).takeWhile {
                            sheetIndex++
                            verticesIndex++
                            sheetList.add(it)
                            (sheetIndex <= sectionVerticesCount && verticesIndex < splinePoints.size)
                        }
                        sectionList.add(Section(pointToTypeWindow[0].type, sheetList))
                    }
                    else -> {
                        var coilIndex = 0
                        val bendList = ArrayList<Vector3f>(sectionVerticesCount)
                        splinePoints.drop(verticesIndex).takeWhile {
                            coilIndex++
                            verticesIndex++
                            bendList.add(it)
                            (coilIndex <= sectionVerticesCount && verticesIndex < splinePoints.size)
                        }
                        sectionList.add(Section(pointToTypeWindow[0].type, bendList))
                    }
                }
            }
            val finalSectionList = ArrayList<Section>(sectionList.size)
            var i = 0
            while(i < sectionList.lastIndex) {
                val helpList = ArrayList<Vector3f>(sectionVerticesCount*8)
                sectionList.drop(i).takeWhile {
                    helpList.addAll(it.pointsOfSection)
                    if(i < sectionList.lastIndex) {
                        i++
                        if(it.type != sectionList[i].type) {
                            val summarizedSectionList = ArrayList<Vector3f>(helpList.size)
                            summarizedSectionList.addAll(helpList)
                            finalSectionList.add(Section(it.type, summarizedSectionList))
                            helpList.clear()
                            true
                        }
                        else { true }
                    }
                    else {
                        //edge case of the last point having a different type than its predecessor
                        if(it.type != sectionList.dropLast(1).last().type) {
                            finalSectionList.add(it)
                        }
                        false }
                }
            }
            finalSectionList.forEach {
                when {
                    (it.type == SecStrucType.helix4) -> {
                        it.pointsOfSection.forEach {
                            val rectangle = ArrayList<Vector3f>(4)
                            rectangle.add(Vector3f(1f, 0.1f, 0f))
                            rectangle.add(Vector3f(-1f, 0.1f, 0f))
                            rectangle.add(Vector3f(-1f, -0.1f, 0f))
                            rectangle.add(Vector3f(1f, -0.1f, 0f))
                            baseShapeList.add(rectangle)
                        }
                    }
                    (it.type.isBetaStrand) -> {
                        val sheetSize = it.pointsOfSection.size
                        val seventyeightPercent = (sheetSize*0.78).toInt()
                        for (j in 0 until seventyeightPercent) {
                            val list = ArrayList<Vector3f>(4)
                            list.add(Vector3f(0.1f, 1f, 0f))
                            list.add(Vector3f(-0.1f, 1f, 0f))
                            list.add(Vector3f(-0.1f, -1f, 0f))
                            list.add(Vector3f(0.1f, -1f, 0f))
                            baseShapeList.add(list)
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
                        it.pointsOfSection.forEach {
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
                            baseShapeList.add(octagon)
                        }
                    }
                }
            }
            return baseShapeList

        }
        return Curve(spline) { baseShape() }
    }

    /**
     * Inline function to make a Vector out of an atom position, as for now we do not
     * need any information about an atom besides its name and its position
     */
    private fun Atom.getVector(): Vector3f {
        return Vector3f(this.x.toFloat(), this.y.toFloat(), this.z.toFloat())
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
     * data class containing two different B-Splines, which later will be
     * melted into one.
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
        val finalSpline = ArrayList<Vector3f>(guidePoints.size*sectionVerticesCount)
        val skeleton = splineSkeleton(guidePoints)
        val spline1 = UniformBSpline(skeleton.splineSkeleton1, sectionVerticesCount).splinePoints()
        val spline2 = UniformBSpline(skeleton.splineSkeleton2, sectionVerticesCount).splinePoints()
        spline1.forEachIndexed{ i, _ ->
            val splinePoint = Vector3f()
            spline1[i].add(spline2[i], splinePoint)
            splinePoint.mul(0.5f)
            finalSpline.add(splinePoint)
        }
        return DummySpline(finalSpline)
    }

    /**
     * data class for the GuidePoints
     */
    data class GuidePoint(val finalPoint: Vector3f, val cVec: Vector3f, val dVec: Vector3f,
                          val offset: Float = 0f, var widthFactor: Float = 0f,
                          val prevResidue: Group?, val nextResidue: Group?, var type: SecStrucType)

    /**
     * Calculates the GuidePoints from the list of amino acids.
     */
    private fun calculateGuidePoints(aminoList: List<Group>): ArrayList<GuidePoint> {
        //To include all points in the visualization, dummy points need to be made.
        //First, a list without these dummy points is calculated.
        val guidePointsWithoutDummy = ArrayList<GuidePoint>(aminoList.size-1)
        val guidePoints = ArrayList<GuidePoint>(aminoList.size + 4 -1)
        val maxOffSet = 1.5f
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
            /*
             Ribbons widen in areas of high curvature, respectively helices, and
             low curvature, respectively beta sheets. The factors are listed in
             the table* below:

             CA-CA DIST  WIDTH FACTOR    OFFSET FACTOR   NOTE
             ==========  ============    =============   ====
               5.0         1               1               ~limit for curled-up protein
               5.5         1               1               } linear interpolation
               7.0         0               0               } from 1.0 to 0.0
               9.0         0               0               } linear interpolation
               10.5        1               0               } from 1.0 to 0.0
               11.0        1               0               ~limit for extended protein

               *Copyright (C) 2002-2007 Ian W. Davis & Vincent B. Chen. All rights reserved
             */
            when {
                (i >= 1 && i < aminoList.size - 3) -> {
                    val ca0 = aminoList[i - 1].getAtom("CA").getVector()
                    val ca3 = aminoList[i + 2].getAtom("CA").getVector()
                    val ca0Ca3 = Vector3f()
                    ca0.sub(ca3, ca0Ca3)
                    val ca0Ca3Distance = ca0.distance(ca3)
                    when {
                        (ca0Ca3Distance < 7f) -> {
                            widthFactor = min(1.5f, 7f - ca0Ca3Distance) / 1.5f
                            offset = widthFactor
                            val ca0Ca3Midpoint = Vector3f()
                            ca0Ca3.mul(0.5f, ca0Ca3Midpoint)
                            val offsetVec = Vector3f()
                            finalPoint.sub(ca0Ca3Midpoint, offsetVec)
                            offsetVec.normalize().mul(offset * maxOffSet)
                            finalPoint.add(offsetVec)
                        }
                        (ca0Ca3Distance > 9f) -> {
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
                    aminoList[i], aminoList[i+1], SecStrucType.bend))
        }

        val secStrucs = dssp()
        //This map is a necessary parameter for the range calculation
        val map = AtomPositionMap(structure)

        //Then we add the secondary structures from the dssp
        secStrucs.forEach { secStruc ->
            guidePointsWithoutDummy.forEach {guide ->
                for( i in 0 .. secStruc.range.length) {
                    //BioJava uses residueNumber as an identifier for each residue
                    if(secStruc.range.getResidue(i, map) == guide.nextResidue!!.residueNumber) {
                        guide.type = secStruc.type
                    }
                }
            }
        }

        //only assign widthFactor if there are three guide points with one in a row
        guidePointsWithoutDummy.windowed(5, 1) { window ->
            when {
                (window[0].widthFactor == 0f && window[4].widthFactor == 0f) -> {
                    if (window[1].widthFactor == 0f || window[2].widthFactor == 0f || window[3].widthFactor == 0f) {
                        window[1].widthFactor = 0f
                        window[2].widthFactor = 0f
                        window[3].widthFactor = 0f
                    }
                }
                //TODO make it more finegrained so that there is not one section bends in a secondary struc
                (!window[0].type.isHelixType && !window[0].type.isBetaStrand
                        && !window[4].type.isHelixType && !window[4].type.isBetaStrand) -> {
                    for(i in 1..3) {
                        if(!window[i].type.isHelixType || !window[i].type.isBetaStrand) {
                            window[1].type = SecStrucType.bend
                            window[2].type = SecStrucType.bend
                            window[3].type = SecStrucType.bend
                        }
                    }
                }
            }


        }
        //if there is a width factor is still assigned at the beginning, also assign it to the first point
        if(guidePointsWithoutDummy[1].widthFactor != 0f && guidePointsWithoutDummy[2].widthFactor != 0f &&
                guidePointsWithoutDummy[3].widthFactor != 0f) {
            guidePointsWithoutDummy[0].widthFactor = guidePointsWithoutDummy[1].widthFactor
            guidePointsWithoutDummy[0].type = guidePointsWithoutDummy[0].type
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
                guidePointsWithoutDummy[0].offset, guidePointsWithoutDummy[0].widthFactor, aminoList[0], aminoList[0],
                guidePointsWithoutDummy[0].type))
        guidePoints.add(GuidePoint(caBegin, guidePointsWithoutDummy[0].cVec, guidePointsWithoutDummy[0].dVec,
                guidePointsWithoutDummy[0].offset, guidePointsWithoutDummy[0].widthFactor, aminoList[0], aminoList[0],
                guidePointsWithoutDummy[0].type))
        //add all guide points from the previous calculation
        guidePoints.addAll(guidePointsWithoutDummy)
        //add dummy points at the end
        val caEnd = aminoList.last().getAtom("CA").getVector()
        guidePoints.add(GuidePoint(caEnd,
                guidePointsWithoutDummy.last().cVec, guidePointsWithoutDummy.last().dVec,
                guidePointsWithoutDummy.last().offset, guidePointsWithoutDummy.last().widthFactor,
                aminoList.last(), aminoList.last(), guidePointsWithoutDummy.last().type))
        val dummyVecEnd = caEnd.randomFromVector()
        guidePoints.add(GuidePoint(dummyVecEnd,
                guidePointsWithoutDummy.last().cVec, guidePointsWithoutDummy.last().dVec,
                guidePointsWithoutDummy.last().offset, guidePointsWithoutDummy.last().widthFactor,
                aminoList.last(), aminoList.last(), guidePointsWithoutDummy.last().type))

        return untwistRibbon(guidePoints)
    }

    /**
     * The calculation of the helices results in twists of the curve. This function
     * takes a list of GuidePoints and untwists them.
     */
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
