package graphics.scenery

import cleargl.GLVector
import org.biojava.nbio.structure.*
import org.biojava.nbio.structure.io.PDBFileReader
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.InvalidPathException
/**
 * Constructs a protein from a pdb-file.
 * @author  Justin Buerger <burger@mpi-cbg.de>
 * @param [fromID] loads a pbd-file with an ID. See also: https://www.rcsb.org/pages/help/advancedsearch/pdbIDs
 * @param [fromFile] loads a pdb-file from memory.
 */

class Protein(val structure: Structure): Mesh("Protein") {

    companion object myProtein {

        fun fromID(id: String): Protein {
                //print("Please enter the PDB-ID: ")
                //val id = readLine()
                val struc = try { StructureIO.getStructure(id) }
                catch (struc: IOException) {
                    print("Something went wrong with the loading- are you sure you chose the right ID?")
                    struc.printStackTrace()
                }
                catch(struc: StructureException) {
                    print("Something went wrong with the loading.")
                    struc.printStackTrace()
                }
                finally {
                    val struc = StructureIO.getStructure(id)
                    val protein = Protein(struc)
                    return protein
                }
        }

        fun fromFile(path: String   ): Protein {
            val reader = PDBFileReader()
            //print("Please enter the path of your PDB-File: ")
            //val readPath = readLine()
            val struc = try { reader.getStructure(path) }
            catch (struc: InvalidPathException) {
                print("Path was invalid, maybe this helps: ${struc.reason} " +
                    "or the index: ${struc.index}")
            }
            catch(struc: FileNotFoundException) {
                print("The File is not in the directory")
            }
            catch(struc: Exception) {
                print("Something went wrong, sorry!")
            }

            finally {
                val struc = reader.getStructure(path)
                val protein = Protein(struc)
                return protein
            }

        }
    }

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

        //val bonds: MutableList<Bond> = atoms.filter { it.bonds != null }.flatMap { it.bonds }.toMutableList()


        val aminoBondClass = AminoAcidBonds(structure)

        val cylinders = aminoBondClass.aminoBonds().map {
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
