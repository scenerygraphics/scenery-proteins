package unit

import graphics.scenery.Protein
import graphics.scenery.proteins.RibbonDiagram
import graphics.scenery.utils.LazyLogger
import org.biojava.nbio.structure.Group
import org.junit.Test
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.jvm.isAccessible
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
    fun numberResiduesTest() {
        logger.info("Tests coherence of curve size and number of residues.")
        val plantProtein = Protein.fromID("3nir")
        val ribbon = RibbonDiagram(plantProtein)
        ribbon.callPrivateFunc("")

    }

}

//Inline function for the protein to access residues
private fun Protein.getResidues(): ArrayList<ArrayList<Group>> {
    val proteins = ArrayList<ArrayList<Group>>(this.structure.chains.size)
    this.structure.chains.forEach{ chain ->
        if(chain.isProtein) {
            val aminoList = ArrayList<Group>(chain.atomGroups.size)
            chain.atomGroups.forEach { group ->
                if (group.hasAminoAtoms()) {
                    aminoList.add(group)
                }
            }
            proteins.add(aminoList)
        }
    }
}

//Inline function to access private function in the RibbonDiagram
private inline fun <reified T> T.callPrivateFunc(name: String, vararg args: Any?): Any? =
        T::class
                .declaredMemberFunctions
                .firstOrNull { it.name == name }
                ?.apply { isAccessible = true }
                ?.call(this, *args)