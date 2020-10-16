package unit

import graphics.scenery.numerics.Random
import graphics.scenery.proteins.Axis
import graphics.scenery.utils.LazyLogger
import org.junit.Test
import kotlin.test.assertFails

/**
 * This is the test for the axis class
 */
class AxisTests {
    private val logger by LazyLogger()

    @Test
    fun testTooFewAtoms() {
        val list = listOf(Random.random3DVectorFromRange(-10f, 10f),Random.random3DVectorFromRange(-10f, 10f),
                Random.random3DVectorFromRange(-10f, 10f))
        assertFails { Axis(list) }
    }

}