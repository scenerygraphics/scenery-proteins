package unit

import graphics.scenery.numerics.Random
import graphics.scenery.proteins.CatmullRomSpline
import graphics.scenery.proteins.Helix
import graphics.scenery.proteins.MathLine
import graphics.scenery.utils.LazyLogger
import org.joml.Vector3f
import org.junit.Test
import kotlin.test.assertFails

/**
 * This is the test class for the Helix class.
 */

class HelixTests {
    private val logger by LazyLogger()

    @Test
    fun testNullVector() {
        logger.info("Test whether the exception gets thrown when the direction is the null vector.")
        val line = MathLine(Vector3f(0f, 0f, 0f), Random.random3DVectorFromRange(-10f, 10f))
        val point1 = Random.random3DVectorFromRange( -30f, -10f)
        val point2 = Random.random3DVectorFromRange( -9f, 20f)
        val point3 = Random.random3DVectorFromRange( 21f, 30f)
        val point4 = Random.random3DVectorFromRange( 31f, 100f)
        val controlPoints = arrayListOf(point1, point2, point3, point4)
        val spline = CatmullRomSpline(controlPoints)
        val list = ArrayList<Vector3f>()
        list.add(Vector3f(0.3f, 0.3f, 0f))
        list.add(Vector3f(0.3f, -0.3f, 0f))
        list.add(Vector3f(-0.3f, -0.3f, 0f))

        assertFails { Helix(line, spline) { list } }
    }


}