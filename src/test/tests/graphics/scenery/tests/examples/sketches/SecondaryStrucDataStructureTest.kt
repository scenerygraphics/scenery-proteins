package graphics.scenery.tests.examples.sketches

import graphics.scenery.Protein
import graphics.scenery.proteins.BackBoneSticks
import org.junit.Test

class RibbonDiagramTest {

    @Test
    fun main() {
        val protein = Protein.fromID("3nir")
        val ss = BackBoneSticks(protein)
        ss.backBoneSticks()
    }
}