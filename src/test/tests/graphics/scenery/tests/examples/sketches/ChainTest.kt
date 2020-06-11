package graphics.scenery.tests.examples.sketches

import graphics.scenery.Protein
import org.junit.Test

class ChainTest {

    @Test
    fun test() {
        val protein = Protein.fromID("2zzm")
        val structure = protein.structure
        val chains = structure.chains
        chains.forEach{ println(it.atomGroups)}
    }
}