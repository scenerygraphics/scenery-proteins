package graphics.scenery.proteins

import graphics.scenery.Mesh
import graphics.scenery.Protein
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.Structure
import org.biojava.nbio.structure.StructureIO
import org.biojava.nbio.structure.align.util.AtomCache
import org.biojava.nbio.structure.io.FileParsingParameters
import org.biojava.nbio.structure.secstruc.DSSPParser
import org.biojava.nbio.structure.secstruc.SecStrucCalc
import org.biojava.nbio.structure.secstruc.SecStrucInfo
import org.biojava.nbio.structure.secstruc.SecStrucState

class SecondaryStructure(val protein: Protein): Mesh("SecondaryStructure") {

    fun secondaryStruc(): List<SecStrucInfo> {

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

        return secStrucs
    }
}