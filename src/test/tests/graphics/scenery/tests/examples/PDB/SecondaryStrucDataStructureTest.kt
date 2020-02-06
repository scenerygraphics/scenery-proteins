package graphics.scenery.tests.examples.PDB

import graphics.scenery.Protein
import graphics.scenery.proteins.BackBoneSticks
import org.junit.Test

class SecondaryStructureDataStructureTest {

    @Test
    fun main() {
        val protein = Protein.fromID("3nir")
        val ss = BackBoneSticks(protein)
        ss.backBoneSticks()
    }
}