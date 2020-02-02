package graphics.scenery.proteins

import cleargl.GLVector
import graphics.scenery.*
import org.biojava.nbio.structure.Bond
import org.biojava.nbio.structure.BondImpl
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.secstruc.SecStrucCalc
import org.biojava.nbio.structure.secstruc.SecStrucInfo

class SecondaryStructure(val protein: Protein): Mesh("SecondaryStructure") {

    fun secondaryStruc(): Node {

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

        val c = Cylinder(0.0125f, 0.001f, 10)
        c.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
        c.instancedProperties["ModelMatrix"] = {c.model}
        c.material.diffuse = GLVector(1.0f, 1.0f, 1.0f)

        val chains = struc.chains
        val points = ArrayList<GLVector>()

        //calculates the bonds between the amino acids
        chains.forEach{
            val groups = it.atomGroups
            while(groups.size > 1) {
                val groupi = groups[0]
                groupi.atoms.forEach{
                    if(it.name == "C" || it.name == "CA" || it.name == "N") {
                        val point = GLVector(it.x.toFloat(), it.y.toFloat(), it.z.toFloat())
                        points.add(point)
                    }
                }
                groups.removeAt(0)
            }
        }

        val spline = CatmulSpline(points)

        val catmulChain = spline.CatMulRomChain()

        val cylinders = catmulChain.map {
            val section = Mesh()
            section.parent = backBone
            val i = catmulChain.indexOf(it)
            if(i < catmulChain.size) {
                section.orientBetweenPoints(it, catmulChain[i + 1],
                        true, true)
            }
            section.instancedProperties["ModelMatrix1"] = { section.model }
            section
        }
        c.instances.addAll(cylinders)

        backBone.addChild(c)

        return backBone
    }
}