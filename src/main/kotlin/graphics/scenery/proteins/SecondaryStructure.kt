package graphics.scenery.proteins

import cleargl.GLVector
import graphics.scenery.*
import org.biojava.nbio.structure.AtomPositionMap
import org.biojava.nbio.structure.Chain
import org.biojava.nbio.structure.secstruc.*

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
        val sections = ArrayList<Section>(secStrucs.size)
        secStrucs.forEach { secStruc ->
            val points = ArrayList<GLVector>(secStruc.range.length)
            val type = secStruc.type
            groups.forEach { group ->
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
        val chains = struc.chains
        val points = ArrayList<GLVector>()
        val sections = dssp()

        /*
        chains.forEach{
            val groups = it.atomGroups
            while(groups.size > 1) {
                val groupi = groups[0]
                groupi.atoms.forEach{
                    if(it.name == "CA") {
                        val point = GLVector(it.x.toFloat(), it.y.toFloat(), it.z.toFloat())
                        points.add(point)
                    }
                }
                groups.removeAt(0)
            }
        }

        val spline = CatmullRomSpline(points)
        val geo = CurveGeometry(spline)
        fun octagon(): ArrayList<GLVector> {
            val octagon = ArrayList<GLVector>(8)
            val sin45 = sqrt(2f)/40f
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

        fun helixBaseShape(): ArrayList<GLVector> {
            val helix = ArrayList<GLVector>(4)
            helix.add(GLVector(0.05f, 0.5f, 0f))
            helix.add(GLVector(-0.05f, 0.5f, 0f))
            helix.add(GLVector(-0.05f, -0.5f, 0f))
            helix.add(GLVector(0.05f, -0.5f, 0f))
            return helix
        }

        geo.drawSpline { octagon() }
        backBone.addChild(geo)

        return backBone
         */
    }
}