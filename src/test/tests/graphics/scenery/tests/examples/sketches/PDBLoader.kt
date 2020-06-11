package graphics.scenery.tests.examples.sketches

import org.biojava.nbio.structure.io.*
import org.biojava.nbio.structure.*
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.InvalidPathException

/**
 * Simple class to load PDB-Files. There are two ways to load a pdb-file:
 * first, you can load it from local memory, second, you can provide the
 * unique ID each PDB-File has- Biojava will automatically download it.
 */


class PDBLoader {

    val reader = PDBFileReader()

    fun loadPBDbyIDManualinput() {
        print("Please enter the PDB-ID: ")
        val id = readLine()
        val struc = try { StructureIO.getStructure(id) }
        catch (struc: IOException) {
            print("Something went wrong with the loading- are you sure you chose the right ID?")
            struc.printStackTrace()
        }
        catch(struc: StructureException) {
            print("Something went wrong with the loading.")
            struc.printStackTrace()
        }
        print(struc)
    }

    fun loadFromMemoryManualInput() {
        print("Please enter the path of your PDB-File: ")
        val readPath = readLine()
        val pathStruc = try { reader.getStructure(readPath) }
        catch (pathStruc: InvalidPathException) {
            print("Path was invalid, maybe this helps: ${pathStruc.reason} " +
                "or the index: ${pathStruc.index}")
        }
        catch(pathStruc: FileNotFoundException) {
            print("The File is not in the directory")
        }
        catch(pathStruc: Exception) {
            print("Something went wrong, sorry!")
        }
        print(pathStruc)
    }

    fun loadPBDbyID(id: String): Any? {

        val struc = try {
            StructureIO.getStructure(id)
        } catch (struc: IOException) {
            print("Something went wrong with the loading- are you sure you chose the right ID?")
            struc.printStackTrace()
            return null
        } catch (struc: StructureException) {
            print("Something went wrong with the loading.")
            struc.printStackTrace()
            return null
        }
        print(struc)

        return struc
    }

    fun loadFromMemory(path: String): Any? {
        val pathStruc = try { reader.getStructure(path) }
        catch (pathStruc: InvalidPathException) {
            print("Path was invalid, maybe this helps: ${pathStruc.reason} " +
                "or the index: ${pathStruc.index}")
            return null
        }
        catch(pathStruc: FileNotFoundException) {
            print("The File is not in the directory")
            return null
        }
        catch(pathStruc: Exception) {
            print("Something went wrong, sorry!")
            return null
        }
        print(pathStruc)

        return pathStruc
    }

}
