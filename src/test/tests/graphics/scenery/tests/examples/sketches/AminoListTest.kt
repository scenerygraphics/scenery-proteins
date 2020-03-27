package graphics.scenery.tests.examples.sketches

import graphics.scenery.proteins.AminoList
import org.junit.Test


class AminoListTest {

    @Test
   fun main() {
        val bonds = AminoList()
        print(bonds.aminoAcids())
    }
}