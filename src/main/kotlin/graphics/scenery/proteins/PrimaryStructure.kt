package graphics.scenery.proteins

import cleargl.GLVector
import graphics.scenery.*
import org.biojava.nbio.structure.*

class PrimaryStructure(val structure: Structure): Mesh("PrimaryStructure") {

    fun primaryStruc(): Node {

        val atoms: Array<Atom> = StructureTools.getAllAtomArray(structure)

        val atomMasters = HashMap<Element, Node>()
        Element.values().forEach {
            val s = Icosphere(0.05f, 2)
            s.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
            s.instancedProperties["ModelMatrix"] = { s.world }
            atomMasters[it] = s
        }

        atoms.forEach {
            val s = Node()
            s.position = (GLVector(it.x.toFloat(), it.y.toFloat(), it.z.toFloat()))
            s.instancedProperties["ModelMatrix"] = { s.world }

            when (it.element) {
                Element.H -> {
                    s.material.diffuse = GLVector(1.0f, 1.0f, 1.0f)
                }
                Element.C -> {
                    s.material.diffuse = GLVector(0.0f, 0.0f, 0.0f)
                }
                Element.N -> {
                    s.material.diffuse = GLVector(0.0f, 0.0f, 1.0f)
                }
                Element.O -> {
                    s.material.diffuse = GLVector(1.0f, 0.0f, 0.0f)
                }
                else -> {
                    s.material.diffuse = GLVector(1.0f, 0.2f, 0.8f)
                }
            }

            val master = atomMasters[it.element]
            master?.instances?.add(s)
        }

        val primaryStruc = Node("Primary Structure")
        atomMasters.filter { it.value.instances.isNotEmpty() }
                .forEach { primaryStruc.addChild(it.value) }

        val c = Cylinder(0.025f, 1.0f, 10)
        c.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
        c.instancedProperties["ModelMatrix1"] = { c.model }
        c.material.diffuse = GLVector(1.0f, 1.0f, 1.0f)

        val bonds: MutableList<Bond> = atoms.filter { it.bonds != null }.flatMap { it.bonds }.toMutableList()

        val aminoList = AminoList()
        val chains = structure.chains
        val groups = chains.flatMap { it.atomGroups }

        //This creates bonds for all the amino acids stored in the pdb
        aminoList.aminoAcids().forEach {
            val name = it.name
            //please not that these bonds are not the bonds stored in the pdb-file but the hardcoded bonds from the AminoList
            val bondList = it.bonds
            //TODO maybe the few lines below can be written more elegant?
            groups.forEach{
                val atoms = it.atoms
                if(it.pdbName == name){
                    bondList.forEach { val triple = it
                        atoms.forEach{ val atom1 = it
                            atoms.forEach {
                                val atom2 = it
                                if((atom1.name + "'") == triple.first && (atom2.name +"'") == triple.second){
                                    val bond = BondImpl(atom1, atom2, triple.third)
                                    bonds.add(bond)
                                }
                            }}
                    }
                }
            }
        }

        //calculates the bonds between amino acids
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

        val cylinders = bonds.map {
            val bond = Mesh()
            bond.parent = primaryStruc
            val atomA = it.atomA
            val atomB = it.atomB
            bond.orientBetweenPoints(GLVector(atomA.x.toFloat(), atomA.y.toFloat(), atomA.z.toFloat()),
                    GLVector(atomB.x.toFloat(), atomB.y.toFloat(), atomB.z.toFloat()), true, true)
            bond.instancedProperties["ModelMatrix1"] = { bond.model }
            bond
        }
        c.instances.addAll(cylinders)

        primaryStruc.addChild(c)

        return primaryStruc

    }
}