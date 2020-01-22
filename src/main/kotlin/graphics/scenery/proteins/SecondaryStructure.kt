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

        val c = Cylinder(0.025f, 1.0f, 10)
        c.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
        c.instancedProperties["ModelMatrix"] = {c.model}
        c.material.diffuse = GLVector(1.0f, 1.0f, 1.0f)

        val chains = struc.chains
        val groups = chains.flatMap { it.atomGroups }
        val bonds = ArrayList<Bond>()

        //calculates the bonds between the amino acids
        chains.forEach{
            val groups = it.atomGroups
            while(groups.size > 1) {
                val group1 = groups[0]
                val group2 = groups[1]
                group1.atoms.forEach{
                    val atom1 = it
                    group2.atoms.forEach{
                        val atom2 = it
                        if(atom1.name == "C" && atom2.name == "N") {
                            val bond = BondImpl(atom1, atom2, 1)
                            bonds.add(bond)
                        }
                    }
                }
                groups.removeAt(0)
            }
        }

        //calculates the bonds within the amino acid (only the back bone)
        groups.forEach {
            val atoms = it.atoms
            atoms.forEach{
                val atom1 = it
                atoms.forEach{
                    val atom2 = it
                    if((atom1.name == "CA" && atom2.name == "N") || (atom1.name == "CA" && atom2.name == "C")) {
                        val bond = BondImpl(atom1, atom2, 1)
                        bonds.add(bond)
                    }
                }
            }
        }

        val cylinders = bonds.map {
            val bond = Mesh()
            bond.parent = backBone
            val atomA = it.atomA
            val atomB = it.atomB
            bond.orientBetweenPoints(GLVector(atomA.x.toFloat(), atomA.y.toFloat(), atomA.z.toFloat()),
                    GLVector(atomB.x.toFloat(), atomB.y.toFloat(), atomB.z.toFloat()), true, true)
            bond.instancedProperties["ModelMatrix1"] = { bond.model }
            bond
        }
        c.instances.addAll(cylinders)

        backBone.addChild(c)

        return backBone
    }


}