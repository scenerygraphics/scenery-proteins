package graphics.scenery.tests.examples.PDB

import graphics.scenery.proteins.BondsMap
import org.junit.Test


class BondTest {

    @Test
   fun main(args: Array<String>) {
        val bonds = BondsMap()
        print(bonds.bonds())
    }
}