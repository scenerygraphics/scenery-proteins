package graphics.scenery.proteins

import cleargl.GLVector
import com.jogamp.opengl.math.FloatUtil.sqrt
import graphics.scenery.*
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.secstruc.SecStrucCalc
import org.biojava.nbio.structure.secstruc.SecStrucInfo

class SecondaryStructure(val protein: Protein): Mesh("SecondaryStructure") {

    fun backBone(): Node {

        val struc = protein.structure
        val ssc = SecStrucCalc()
        ssc.calculate(struc, true)

        val secStrucs = ArrayList<SecStrucInfo>()
        struc.chains.forEach{
            it.atomGroups.forEach{
                if(it.hasAminoAtoms()){
                    val ssInfo: SecStrucInfo = it.getProperty(Group.SEC_STRUC) as SecStrucInfo
                    secStrucs.add(ssInfo)
                }
            }
        }

        val backBone = Node("BackBone")

        val chains = struc.chains
        val points = ArrayList<GLVector>()

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
        geo.drawSpline { octagon() }
        backBone.addChild(geo)

        return backBone
    }
}