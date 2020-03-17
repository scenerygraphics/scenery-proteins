package graphics.scenery.proteins

import cleargl.GLVector
import graphics.scenery.*
import org.biojava.nbio.structure.AtomPositionMap
import org.biojava.nbio.structure.Chain
import org.biojava.nbio.structure.secstruc.*
import java.lang.Math.sqrt

class SecondaryStructure(val protein: Protein): Mesh("SecondaryStructure") {

    private val struc = protein.structure
    private val chains: MutableList<Chain> = struc.chains
    private val groups = chains.flatMap { it.atomGroups }

    private fun dssp(): List<SecStrucElement> {
        //see: https://github.com/biojava/biojava-tutorial/blob/master/structure/secstruc.md
        val ssc = SecStrucCalc()
        ssc.calculate(struc, true)
        return SecStrucTools.getSecStrucElements(struc)
    }

    data class Section(val controlpoints: ArrayList<GLVector>, val Type: SecStrucType)

    private fun sections(): ArrayList<Section> {
        val secStrucs = dssp()
        //This map is a necessary parameter for the range calculation
        val map = AtomPositionMap(struc)
        val sections = ArrayList<Section>(secStrucs.size + groups.size)

        //first we add the whole backBone
        val allPoints = ArrayList<GLVector>(groups.size)
        groups.flatMap { it.atoms }.forEach {
            if(it.name == "CA") {
                allPoints.add(GLVector(it.x.toFloat(), it.y.toFloat(), it.z.toFloat()))
            }
        }
        sections.add(Section(allPoints, SecStrucType.coil))

        //Then we add the secondary structures from the dssp
        secStrucs.forEach { secStruc ->
            val points = ArrayList<GLVector>(secStruc.range.length)
            val type = secStruc.type
            //The dssp is not exhaustive; therefore, we need to make sure every group is included
            groups.forEachIndexed { index, group ->
                val r = group.residueNumber
                if(secStruc.range.contains(r, map)) {
                    group.atoms.forEach{
                        if(it.name == "CA") {
                            points.add(GLVector(it.x.toFloat(), it.y.toFloat(), it.z.toFloat()))
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
        val sections = dssp()

        //baseShapes
        /**
         * This is the baseShape for the backbone: a simple octagon.
         */
        fun octagon(): ArrayList<GLVector> {
            val octagon = ArrayList<GLVector>(8)
            val sin45 = kotlin.math.sqrt(2f) /40f
            octagon.add(GLVector(0.05f, 0f, 0f))
            octagon.add(GLVector(sin45, sin45, 0f))
            octagon.add(GLVector(0f, 0.05f, 0f))
            octagon.add(GLVector(-sin45, sin45, 0f))
            octagon.add(GLVector(-0.05f, 0f, 0f))
            octagon.add(GLVector(-sin45, -sin45, 0f))
            octagon.add(GLVector(0f, -0.05f, 0f))
            octagon.add(GLVector(sin45, -sin45, 0f))
            return octagon
        }

        /**
         * This is the baseShape for the helices: a rectangle.
         */
        fun helixBaseShape(): ArrayList<GLVector> {
            val helix = ArrayList<GLVector>(4)
            helix.add(GLVector(0.05f, 0.5f, 0f))
            helix.add(GLVector(-0.05f, 0.5f, 0f))
            helix.add(GLVector(-0.05f, -0.5f, 0f))
            helix.add(GLVector(0.05f, -0.5f, 0f))
            return helix
        }

        val spline = CatmullRomSpline(allPoints)
        val geo = CurveGeometry(spline)
        geo.drawSpline { octagon() }
        backBone.addChild(geo)

        return backBone
    }
}