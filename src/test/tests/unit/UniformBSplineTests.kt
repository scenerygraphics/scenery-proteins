package unit

import graphics.scenery.numerics.Random
import graphics.scenery.proteins.UniformBSpline
import graphics.scenery.utils.LazyLogger
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UniformBSplineTests {
    private val logger by LazyLogger()

    @Test
    fun testLength() {
        logger.info("This is the test for the Length of the chain.")
        val point1 = Random.randomVectorFromRange(3, -30f, -10f)
        val point2 = Random.randomVectorFromRange(3, -9f, 20f)
        val point3 = Random.randomVectorFromRange(3, 21f, 30f)
        val point4 = Random.randomVectorFromRange(3, 31f, 100f)

        val controlPoints = arrayListOf(point1, point2, point3, point4)

        val bSpline = UniformBSpline(controlPoints)
        val spline = bSpline.splinePoints()

        assertNotNull(spline)
        assertEquals(spline.size, ((controlPoints.size-3)*100 +1))
    }

}