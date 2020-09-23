package unit

import bdv.cache.CacheControl
import graphics.scenery.Protein
import graphics.scenery.proteins.DummySpline
import graphics.scenery.proteins.RibbonDiagram
import graphics.scenery.utils.LazyLogger
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.secstruc.SecStrucElement
import org.junit.Test
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.jvm.isAccessible
import kotlin.test.assertEquals
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
    fun residueCountTest() {
        logger.info("Tests coherence of curve size and number of residues.")
        val plantProtein = Protein.fromID("3nir")
        val plantRibbon = RibbonDiagram(plantProtein)
        val dsspPlant = plantRibbon.callPrivateFunc("dssp")
        val plantChains = plantProtein.getResidues()
        var allPlantPoints = 0
        plantChains.forEach {
            val guides = RibbonDiagram.GuidePointCalculation.calculateGuidePoints(it, dsspPlant as List<SecStrucElement>)
            val spline = plantRibbon.callPrivateFunc("ribbonSpline", guides) as DummySpline
            allPlantPoints += spline.splinePoints().size
        }
        assertEquals(allPlantPoints, (46)*(10+1))

        val saccharomycesCerevisiae = Protein.fromID("6zqd")
        val scRibbon = RibbonDiagram(saccharomycesCerevisiae)
        val dsspSC = scRibbon.callPrivateFunc("dssp")
        val scChains = saccharomycesCerevisiae.getResidues()
        var allSCPoints = 0
        scChains.forEach {
            val guides = RibbonDiagram.GuidePointCalculation.calculateGuidePoints(it, dsspSC as List<SecStrucElement>)
            val spline = scRibbon.callPrivateFunc("ribbonSpline", guides) as DummySpline
            allSCPoints += spline.splinePoints().size
        }
        assertEquals(allSCPoints, (23448)*(10+1))

    }

    /**
     * Tests number of subProteins.
     */
    @Test
    fun numberSubProteinsTest() {
        logger.info("Tests number of subProteins.")
        val plantProtein = Protein.fromID("3nir")
        val plantRibbon = RibbonDiagram(plantProtein)
        assertEquals(plantRibbon.children.size, 1)

        val insectWing = Protein.fromID("2w49")
        val insectWingRibbon = RibbonDiagram(insectWing)
        assertEquals(insectWingRibbon.children.size, 36)

        val saccharomycesCerevisiae = Protein.fromID("6zqd")
        val scRibbon = RibbonDiagram(saccharomycesCerevisiae)
        assertEquals(scRibbon.children.size, 63)

        val covid19 = Protein.fromID("6zcz")
        val covidRibbon = RibbonDiagram(covid19)
        assertEquals(covidRibbon.children.size, 4)

        val aspirin = Protein.fromID("6mqf")
        val aspirinRibbon = RibbonDiagram(aspirin)
        assertEquals(aspirinRibbon.children.size, 2)

        val nucleosome = Protein.fromID("6y5e")
        val nucRibbon = RibbonDiagram(nucleosome)
        assertEquals(nucRibbon.children.size, 9)
    }

    /**
     * Tests a lot of pdb structures and check that everyone of them yields a valid output.
     * The comments refer to the chosen filter in the "SCIENTIFIC NAME OF SOURCE ORGANISM" category
     * in the RCSB data base. These organisms as well as the pdb files have been chosen arbitrary.
     * The null checks are there to satisfy the test structure- the test verifies in fact that no
     * exception is thrown.
     */
    @Test
    fun testLotsOfProteins() {
        logger.info("Tests a lot of pdb files.")
        //Homo sapiens protein
        val p1 = Protein.fromID("6l69")
        val r1 = RibbonDiagram(p1)
        assertNotNull(r1)
        val p2 = Protein.fromID("3mbw")
        val r2 = RibbonDiagram(p2)
        assertNotNull(r2)
        val p3 = Protein.fromID("4u1a")
        val r3 = RibbonDiagram(p3)
        assertNotNull(r3)
        val p4 = Protein.fromID("5m9m")
        val r4 = RibbonDiagram(p4)
        assertNotNull(r4)
        val p5 = Protein.fromID("6mzl")
        val r5 = RibbonDiagram(p5)
        assertNotNull(r5)
        val p6 = Protein.fromID("6mp5")
        val r6 = RibbonDiagram(p6)
        assertNotNull(r6)
        val p7 = Protein.fromID("2qd4")
        val r7 = RibbonDiagram(p7)
        assertNotNull(r7)
        val p8 = Protein.fromID("6pe9")
        val r8 = RibbonDiagram(p8)
        assertNotNull(r8)
        val p9 = Protein.fromID("1ydk")
        val r9 = RibbonDiagram(p9)
        assertNotNull(r9)
        val p10 = Protein.fromID("2rma")
        val r10 = RibbonDiagram(p10)
        assertNotNull(r10)
        val p11 = Protein.fromID("3mdc")
        val r11 = RibbonDiagram(p11)
        assertNotNull(r11)
        val p12 = Protein.fromID("2kne")
        val r12 = RibbonDiagram(p12)
        assertNotNull(r12)
        val p13 = Protein.fromID("4tn7")
        val r13 = RibbonDiagram(p13)
        assertNotNull(r13)
        val p14 = Protein.fromID("3mao")
        val r14 = RibbonDiagram(p14)
        assertNotNull(r14)
        val p15 = Protein.fromID("5m8s")
        val r15 = RibbonDiagram(p15)
        assertNotNull(r15)
        //Escherichia coli K-12 protein
        val p16 = Protein.fromID("6v2e")
        val r16 = RibbonDiagram(p16)
        assertNotNull(r16)
        val p17 = Protein.fromID("4giz")
        val r17 = RibbonDiagram(p17)
        assertNotNull(r17)
        val p18 = Protein.fromID("3l2j")
        val r18 = RibbonDiagram(p18)
        assertNotNull(r18)
        val p19 = Protein.fromID("4odq")
        val r19 = RibbonDiagram(p19)
        assertNotNull(r19)
        //Human papillomavirus type 31 protein
        val p20 = Protein.fromID("6slm")
        val r20 = RibbonDiagram(p20)
        assertNotNull(r20)
        //Bos taurus protein
        val p21 = Protein.fromID("2qho")
        val r21 = RibbonDiagram(p21)
        assertNotNull(r21)
        val p22 = Protein.fromID("1zr0")
        val r22 = RibbonDiagram(p22)
        assertNotNull(r22)
        val p23 = Protein.fromID("2ake")
        val r23 = RibbonDiagram(p23)
        assertNotNull(r23)
        val p24 = Protein.fromID("2wx1")
        val r24 = RibbonDiagram(p24)
        assertNotNull(r24)
        //Rattus norvegicus protein
        val p25 = Protein.fromID("2mue")
        val r25 = RibbonDiagram(p25)
        assertNotNull(r25)
        val p26 = Protein.fromID("2m0j")
        val r26 = RibbonDiagram(p26)
        assertNotNull(r26)
        val p27 = Protein.fromID("1q5w")
        val r27 = RibbonDiagram(p27)
        assertNotNull(r27)
        val p28 = Protein.fromID("3gj8")
        val r28 = RibbonDiagram(p28)
        assertNotNull(r28)
        val p29 = Protein.fromID("3sui")
        val r29 = RibbonDiagram(p29)
        assertNotNull(r29)
        //Mus musculus protein
        val p30 = Protein.fromID("6pby")
        val r30 = RibbonDiagram(p30)
        assertNotNull(r30)
        val p31 = Protein.fromID("2m0k")
        val r31 = RibbonDiagram(p31)
        assertNotNull(r31)
        val p32 = Protein.fromID("1r4a")
        val r32 = RibbonDiagram(p32)
        assertNotNull(r32)
        val p33 = Protein.fromID("3fub")
        val r33 = RibbonDiagram(p33)
        assertNotNull(r33)
        //Saccharomyces cerevisiae S288C protein
        val p34 = Protein.fromID("6uku")
        val r34 = RibbonDiagram(p34)
        assertNotNull(r34)
        val p35 = Protein.fromID("6v92")
        val r35 = RibbonDiagram(p35)
        assertNotNull(r35)
        val p36 = Protein.fromID("2l2i")
        val r36 = RibbonDiagram(p36)
        assertNotNull(r36)
        val p37 = Protein.fromID("1pyo")
        val r37 = RibbonDiagram(p37)
        assertNotNull(r37)
        val p38 = Protein.fromID("4lcd")
        val r38 = RibbonDiagram(p38)
        assertNotNull(r38)
        //Lama glama protein
        val p39 = Protein.fromID("6p9x")
        val r39 = RibbonDiagram(p39)
        assertNotNull(r39)
        val p40 = Protein.fromID("6uun")
        val r40 = RibbonDiagram(p40)
        assertNotNull(r40)
        val p41 = Protein.fromID("6v80")
        val r41 = RibbonDiagram(p41)
        assertNotNull(r41)
        val p42 = Protein.fromID("6v7z")
        val r42 = RibbonDiagram(p42)
        assertNotNull(r42)
        val p43 = Protein.fromID("6zcz")
        val r43 = RibbonDiagram(p43)
        assertNotNull(r43)
        val p44 = Protein.fromID("4grw")
        val r44 = RibbonDiagram(p44)
        assertNotNull(r44)
        //Oryctolagus cuniculus protein
        val p45 = Protein.fromID("3mc5")
        val r45 = RibbonDiagram(p45)
        assertNotNull(r45)
        val p46 = Protein.fromID("3mbw")
        val r46 = RibbonDiagram(p46)
        assertNotNull(r46)
        val p47 = Protein.fromID("4tkw")
        val r47 = RibbonDiagram(p47)
        assertNotNull(r47)
        val p48 = Protein.fromID("4u0i")
        val r48 = RibbonDiagram(p48)
        assertNotNull(r48)
        val p49 = Protein.fromID("3mas")
        val r49 = RibbonDiagram(p49)
        assertNotNull(r49)
        //Suf scrofa protein
        val p50 = Protein.fromID("6znn")
        val r50 = RibbonDiagram(p50)
        assertNotNull(r50)
        val p51 = Protein.fromID("1ctp")
        val r51 = RibbonDiagram(p51)
        assertNotNull(r51)
        val p52 = Protein.fromID("3j92")
        val r52 = RibbonDiagram(p52)
        assertNotNull(r52)
        val p53 = Protein.fromID("3jak")
        val r53 = RibbonDiagram(p53)
        assertNotNull(r53)
        val p54 = Protein.fromID("1nb5")
        val r54 = RibbonDiagram(p54)
        assertNotNull(r54)
        //Gallus gallus protein
        val p55 = Protein.fromID("3lk3")
        val r55 = RibbonDiagram(p55)
        assertNotNull(r55)
        val p56 = Protein.fromID("1mdu")
        val r56 = RibbonDiagram(p56)
        assertNotNull(r56)
        val p57 = Protein.fromID("3eks")
        val r57 = RibbonDiagram(p57)
        assertNotNull(r57)
        val p58 = Protein.fromID("2ebv")
        val r58 = RibbonDiagram(p58)
        assertNotNull(r58)
        val p59 = Protein.fromID("4gbj")
        val r59 = RibbonDiagram(p59)
        assertNotNull(r59)
        //Danio rerio protein
        val p60 = Protein.fromID("6v4e")
        val r60 = RibbonDiagram(p60)
        assertNotNull(r60)
        val p61 = Protein.fromID("6v4h")
        val r61 = RibbonDiagram(p61)
        assertNotNull(r61)
        val p62 = Protein.fromID("4m8n")
        val r62 = RibbonDiagram(p62)
        assertNotNull(r62)
        val p63 = Protein.fromID("4ia1")
        val r63 = RibbonDiagram(p63)
        assertNotNull(r63)
        val p64 = Protein.fromID("3ei2")
        val r64 = RibbonDiagram(p64)
        assertNotNull(r64)
        //Escherichia virus T4 protein
        val p65 = Protein.fromID("2rh1")
        val r65 = RibbonDiagram(p65)
        assertNotNull(r65)
        val p66 = Protein.fromID("6ps3")
        val r66 = RibbonDiagram(p66)
        assertNotNull(r66)
        val p67 = Protein.fromID("3v2y")
        val r67 = RibbonDiagram(p67)
        assertNotNull(r67)
        val p68 = Protein.fromID("4pla")
        val r68 = RibbonDiagram(p68)
        assertNotNull(r68)
        val p69 = Protein.fromID("3eml")
        val r69 = RibbonDiagram(p69)
        assertNotNull(r69)
        //Staphylococcus aureus protein
        val p70 = Protein.fromID("2seb")
        val r70 = RibbonDiagram(p70)
        assertNotNull(r70)
        val p71 = Protein.fromID("2qej")
        val r71 = RibbonDiagram(p71)
        assertNotNull(r71)
        val p72 = Protein.fromID("1d5m")
        val r72 = RibbonDiagram(p72)
        assertNotNull(r72)
        val p73 = Protein.fromID("2wy8")
        val r73 = RibbonDiagram(p73)
        assertNotNull(r73)
        val p74 = Protein.fromID("4idj")
        val r74 = RibbonDiagram(p74)
        assertNotNull(r74)
        val p75 = Protein.fromID("2vr3")
        val r75 = RibbonDiagram(p75)
        assertNotNull(r75)
        val p76 = Protein.fromID("2win")
        val r76 = RibbonDiagram(p76)
        assertNotNull(r76)
        //Hepacivirus C protein
        val p77 = Protein.fromID("6urh")
        val r77 = RibbonDiagram(p77)
        assertNotNull(r77)
        val p78 = Protein.fromID("3ua7")
        val r78 = RibbonDiagram(p78)
        assertNotNull(r78)
        val p79 = Protein.fromID("3mrn")
        val r79 = RibbonDiagram(p79)
        assertNotNull(r79)
        val p80 = Protein.fromID("4z0x")
        val r80 = RibbonDiagram(p80)
        assertNotNull(r80)
        //Influenza A virus
        val p81 = Protein.fromID("2rhk")
        val r81 = RibbonDiagram(p81)
        assertNotNull(r81)
        val p82 = Protein.fromID("6pdx")
        val r82 = RibbonDiagram(p82)
        assertNotNull(r82)
        val p83 = Protein.fromID("6urm")
        val r83 = RibbonDiagram(p83)
        assertNotNull(r83)
        val p84 = Protein.fromID("2x4q")
        val r84 = RibbonDiagram(p84)
        assertNotNull(r84)
        //Drosophila melanogaster protein
        val p85 = Protein.fromID("1r0n")
        val r85 = RibbonDiagram(p85)
        assertNotNull(r85)
        val p86 = Protein.fromID("2ff6")
        val r86 = RibbonDiagram(p86)
        assertNotNull(r86)
        val p87 = Protein.fromID("4i7b")
        val r87 = RibbonDiagram(p87)
        assertNotNull(r87)
        val p88 = Protein.fromID("3bs5")
        val r88 = RibbonDiagram(p88)
        assertNotNull(r88)
        val p89 = Protein.fromID("5chl")
        val r89 = RibbonDiagram(p89)
        assertNotNull(r89)
        val p90 = Protein.fromID("5f84")
        val r90 = RibbonDiagram(p90)
        assertNotNull(r90)
        val p91 = Protein.fromID("4uuz")
        val r91 = RibbonDiagram(p91)
        assertNotNull(r91)
        val p92 = Protein.fromID("4v98")
        val r92 = RibbonDiagram(p92)
        assertNotNull(r92)
        val p93 = Protein.fromID("4wsi")
        val r93 = RibbonDiagram(p93)
        assertNotNull(r93)
        val p94 = Protein.fromID("4u68")
        val r94 = RibbonDiagram(p94)
        assertNotNull(r94)
        val p95 = Protein.fromID("4aa1")
        val r95 = RibbonDiagram(p95)
        assertNotNull(r95)
        val p96 = Protein.fromID("5jvs")
        val r96 = RibbonDiagram(p96)
        assertNotNull(r96)
        val p97 = Protein.fromID("6hom")
        val r97 = RibbonDiagram(p97)
        assertNotNull(r97)
        val p98 = Protein.fromID("4xib")
        val r98 = RibbonDiagram(p98)
        assertNotNull(r98)
        //Plasmodium falciparum protein
        val p99 = Protein.fromID("4u0q")
        val r99 = RibbonDiagram(p99)
        assertNotNull(r99)
        val p100 = Protein.fromID("6phf")
        val r100 = RibbonDiagram(p100)
        assertNotNull(r100)
        val p101 = Protein.fromID("4v3e")
        val r101 = RibbonDiagram(p101)
        assertNotNull(r101)
        val p102 = Protein.fromID("5bk0")
        val r102 = RibbonDiagram(p102)
        assertNotNull(r102)
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