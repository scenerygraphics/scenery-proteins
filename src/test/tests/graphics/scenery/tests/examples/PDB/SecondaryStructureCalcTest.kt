package graphics.scenery.tests.examples.PDB

import graphics.scenery.Protein
import graphics.scenery.proteins.SecondaryStructure
import org.junit.Test


class SecondaryStructureCalcTest {

    @Test
    fun main() {
        val p = Protein.fromID("3nir")
        val ss = SecondaryStructure(p)
        ss.ribbonDiagram()
    }
}