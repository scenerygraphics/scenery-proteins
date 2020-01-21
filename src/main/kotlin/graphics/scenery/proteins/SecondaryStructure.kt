package graphics.scenery.proteins

import graphics.scenery.Mesh
import graphics.scenery.Protein
import org.biojava.nbio.structure.Structure
import org.biojava.nbio.structure.StructureIO
import org.biojava.nbio.structure.align.util.AtomCache
import org.biojava.nbio.structure.io.FileParsingParameters
import org.biojava.nbio.structure.secstruc.DSSPParser
import org.biojava.nbio.structure.secstruc.SecStrucCalc

class SecondaryStructure(val protein: Protein): Mesh("SecondaryStructure") {

    fun secondaryStruc() {

        val struc = protein.structure
        val ssc = SecStrucCalc()
        ssc.calculate(struc, true)
        
    }
}