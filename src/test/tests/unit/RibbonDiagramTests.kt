package unit

import graphics.scenery.Protein
import graphics.scenery.proteins.RibbonDiagram
import graphics.scenery.utils.LazyLogger
import org.junit.Test
import kotlin.test.assertNotNull

/**
 * This is the test for the RibbonCalculation, i.e. the pdb-support.
 */
class RibbonDiagramTests {
    private val logger by LazyLogger()

    /**
     * Tests coherence of curve size and number of residues.
     */
    @Test
    fun prionTest() {
        logger.info("Tests coherence of curve size and number of residues.")
        val prion = Protein.fromID("2rnm")
        val ribbon = RibbonDiagram(prion)

    }

}
