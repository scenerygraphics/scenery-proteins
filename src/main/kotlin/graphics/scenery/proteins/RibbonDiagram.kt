package graphics.scenery.proteins

import org.joml.*
import graphics.scenery.*
import org.biojava.nbio.structure.AtomPositionMap
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
    private fun sections(): ArrayList<Section> {
        val secStrucs = dssp()
        //This map is a necessary parameter for the range calculation
        val map = AtomPositionMap(struc)
        val sections = ArrayList<Section>(secStrucs.size + groups.size)

        //first we add the whole backBone
        val allPoints = ArrayList<Vector3f>(groups.size)
        groups.flatMap { it.atoms }.forEach {
            if(it.name == "CA") {
                allPoints.add(Vector3f(it.x.toFloat(), it.y.toFloat(), it.z.toFloat()))
            }
        }
        sections.add(Section(allPoints, SecStrucType.bend))

        //Then we add the secondary structures from the dssp
        secStrucs.forEach { secStruc ->
            val points = ArrayList<Vector3f>(secStruc.range.length)
            val type = secStruc.type
            //The dssp is not exhaustive; therefore, we need to make sure every group is included
            groups.forEach {group ->
                for( i in 0 .. secStruc.range.length) {
                    if(secStruc.range.getResidue(i, map) == group.residueNumber) {
                        group.atoms.forEach {
                            if (it.name == "CA") {
                                points.add(Vector3f(it.x.toFloat(), it.y.toFloat(), it.z.toFloat()))
                            }
                        }
                    }
                }
            }
            sections.add(Section(points, type))
        }
        return sections
    }

    /*
    TODO this function should draw a curve along the backbone wth a different baseShape for basic
    TODO backbone, alpha helix, and beta sheet.
     */
    fun ribbonDiagram(): Node {

        val backBone = Node("BackBone")
        val sections = sections()

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

        //TODO BaseShape for beta strands
        fun arrow(): ArrayList<Vector3f> {
            val arrow = ArrayList<Vector3f>(4)
            arrow.add(Vector3f(0.05f, 0.5f, 0f))
            arrow.add(Vector3f(-0.05f, 0.5f, 0f))
            arrow.add(Vector3f(-0.05f, -0.5f, 0f))
            arrow.add(Vector3f(0.05f, -0.5f, 0f))
            return arrow
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