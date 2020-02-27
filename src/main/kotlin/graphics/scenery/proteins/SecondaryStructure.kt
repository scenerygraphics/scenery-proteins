package graphics.scenery.proteins

import cleargl.GLVector
import graphics.scenery.*
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

        /*
        val c = Cylinder(0.00125f, 0.001f, 10)
        c.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
        c.instancedProperties["ModelMatrix"] = {c.model}
        c.material.diffuse = GLVector(1.0f, 1.0f, 1.0f)

         */

        val s = Sphere(0.1f, 2)
        s.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
        s.instancedProperties["ModelMatrix"] =  { s.model }
        s.material.diffuse = GLVector(1.0f, 1.0f, 1.0f)


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

        val catmulChain = spline.CatMulRomChain(n = 10000)
        val geo = CurveGeometry(spline)
        /*
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

         */

        val spheres = catmulChain.map {
            val sphere = Mesh()
            sphere.parent = backBone
            sphere.instancedProperties["ModelMatrix"] = { sphere.model }
            sphere.position = it
            sphere
        }
        s.instances.addAll(spheres)

        backBone.addChild(s)

        return backBone
    }
}