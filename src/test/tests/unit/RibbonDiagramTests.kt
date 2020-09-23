package unit

import graphics.scenery.Protein
import graphics.scenery.proteins.RibbonDiagram
import graphics.scenery.utils.LazyLogger
import org.biojava.nbio.structure.Group
import org.junit.Test
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.jvm.isAccessible
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

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
        val dssp = ribbon.callPrivateFunc("dssp")
        val residues = plantProtein.getResidues()


    }

    /**
     * Tests number of subProteins.
     */
    @Test
    fun numberSubProteinsTest() {
        logger.info("Tests number of subProteins.")
        val plantProtein = Protein.fromID("3nir")
        val plantRibbon = RibbonDiagram(plantProtein)
        assertTrue { plantRibbon.children.size == 1 }

        val insectWing = Protein.fromID("2w49")
        val insectWingRibbon = RibbonDiagram(insectWing)
        assertTrue { insectWingRibbon.children.size == 5 }

        val saccharomycesCerevisiae = Protein.fromID("6zqd")
        val scRibbon = RibbonDiagram(saccharomycesCerevisiae)
        assertTrue { scRibbon.children.size == 60 }

        val covid19 = Protein.fromID("6zcz")
        val covidRibbon = RibbonDiagram(covid19)
        assertTrue { covidRibbon.children.size == 4 }

        val aspirin = Protein.fromID("6mqf")
        val aspirinRibbon = RibbonDiagram(aspirin)
        assertTrue { aspirinRibbon.children.size == 1 }

        val nucleosome = Protein.fromID("6y5e")
        val nucRibbon = RibbonDiagram(nucleosome)
        assertTrue{ nucRibbon.children.size == 2 }

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
    return proteins
}

//Inline function to access private function in the RibbonDiagram
private inline fun <reified T> T.callPrivateFunc(name: String, vararg args: Any?): Any? =
        T::class
                .declaredMemberFunctions
                .firstOrNull { it.name == name }
                ?.apply { isAccessible = true }
                ?.call(this, *args)