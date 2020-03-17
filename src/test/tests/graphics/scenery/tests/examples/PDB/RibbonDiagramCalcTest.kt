package graphics.scenery.tests.examples.PDB

import graphics.scenery.Protein
import graphics.scenery.proteins.RibbonDiagram
import org.junit.Test


class RibbonDiagramCalcTest {

    @Test
    fun main() {
        val p = Protein.fromID("3nir")
        val ss = RibbonDiagram(p)
        ss.ribbonDiagram()
    }
}