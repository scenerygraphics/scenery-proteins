package graphics.scenery.tests.examples.PDB

import graphics.scenery.proteins.AminoList
import graphics.scenery.proteins.SecondaryStructure
import org.junit.Test


class SecondaryStructureCalcTest {

    @Test
    fun main() {
        val ss = SecondaryStructure("3nir")
        ss.secondaryStruc()
    }
}