package graphics.scenery.proteins

import org.joml.*
import graphics.scenery.*
import org.biojava.nbio.structure.AtomPositionMap
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.secstruc.*

/**
 * This class is the Mesh class for the Ribbon Diagram, so it essentially draws a spline along the backbone of
 * a loaded protein and visualizes sheets as arrows and helices with rectangles.
 */
class RibbonDiagram(val protein: Protein): Mesh("SecondaryStructure") {

    private val struc = protein.structure
    private val chains = struc.chains
    private val groups = chains.flatMap { it.atomGroups }

    /**
     * Returns the secondary structures of a protein, calculated with the dssp algorithm. For additional
     * information about the algorithm see https://swift.cmbi.umcn.nl/gv/dssp/
     */
    private fun dssp(): List<SecStrucElement> {
        //see: https://github.com/biojava/biojava-tutorial/blob/master/structure/secstruc.md
        val ssc = SecStrucCalc()
        ssc.calculate(struc, true)
        return SecStrucTools.getSecStrucElements(struc)
    }

    /**
     * This class stores for each section of the protein the locations of its C-Alpha atoms and its
     * type of secondary structure
     */
    data class Section(val controlpoints: ArrayList<Vector3f>, val Type: SecStrucType)

    /**
     * This function calculates the sections of the different secondary structures of the protein.
     */

    /*
    TODO this function should draw a curve along the backbone wth a different baseShape for basic
    TODO backbone, alpha helix, and beta sheet.
     */
    fun ribbonDiagram(): Node {

        val backBone = Node("BackBone")


        //baseShapes
        /**
         * This is the baseShape for the backbone: a simple octagon.
         */
        fun octagon(): ArrayList<Vector3f> {
            val octagon = ArrayList<Vector3f>(8)
            val sin45 = kotlin.math.sqrt(2f) /40f
            octagon.add(Vector3f(0.05f, 0f, 0f))
            octagon.add(Vector3f(sin45, sin45, 0f))
            octagon.add(Vector3f(0f, 0.05f, 0f))
            octagon.add(Vector3f(-sin45, sin45, 0f))
            octagon.add(Vector3f(-0.05f, 0f, 0f))
            octagon.add(Vector3f(-sin45, -sin45, 0f))
            octagon.add(Vector3f(0f, -0.05f, 0f))
            octagon.add(Vector3f(sin45, -sin45, 0f))
            return octagon
        }

        /**
         * This is the baseShape for the helices: a rectangle.
         */
        fun rectangle(): ArrayList<Vector3f> {
            val rectangle = ArrayList<Vector3f>(4)
            rectangle.add(Vector3f(0.5f, 0.05f, 0f))
            rectangle.add(Vector3f(-0.5f, 0.05f, 0f))
            rectangle.add(Vector3f(-0.5f, -0.05f, 0f))
            rectangle.add(Vector3f(0.5f, -0.05f, 0f))
            return rectangle
        }

        fun betaStrand(splineVerticesCount: Int): ArrayList<ArrayList<Vector3f>> {
            val shapeList = ArrayList<ArrayList<Vector3f>>(splineVerticesCount)
            val seventyeightPercent = (splineVerticesCount*0.78).toInt()
            for (i in 0 until seventyeightPercent) {
                val list = ArrayList<Vector3f>()
                list.add(Vector3f(0.1f, 0.5f, 0f))
                list.add(Vector3f(-0.1f, 0.5f, 0f))
                list.add(Vector3f(-0.1f, -0.5f, 0f))
                list.add(Vector3f(0.1f, -0.5f, 0f))
                shapeList.add(list)
            }
            val twentytwoPercent = splineVerticesCount-seventyeightPercent
            for(i in twentytwoPercent downTo 1) {
                val y = 0.8f*i/twentytwoPercent
                val x = 0.1f
                val arrowHeadList = ArrayList<Vector3f>(twentytwoPercent)
                arrowHeadList.add(Vector3f(x, y, 0f))
                arrowHeadList.add(Vector3f(-x, y, 0f))
                arrowHeadList.add(Vector3f(-x, -y, 0f))
                arrowHeadList.add(Vector3f(x, -y, 0f))
                shapeList.add(arrowHeadList)
            }
            return shapeList
        }
        /*
        sections.filter{it.Type  == SecStrucType.helix4}.forEachIndexed { index, (c, t) ->
            val spline = CatmullRomSpline(c)
            val geo = Curve(spline)
            when {
                t.isHelixType -> geo.drawSpline { rectangle() }
                t.isBetaStrand -> geo.drawSpline { arrow() }
                else -> geo.drawSpline { octagon() }
            }
            backBone.addChild(geo)
        }
        
        val spline = CatmullRomSpline(sections[0].controlpoints)
        val geometry = Curve(spline)
        geometry.drawSpline { octagon() }
        backBone.addChild(geometry)
        */
        return backBone
    }
}