package unit

import cleargl.GLVector
import graphics.scenery.proteins.Spline
import graphics.scenery.utils.LazyLogger
import org.junit.Test
import kotlin.test.assertNotNull

class SplineTests {
    private val logger by LazyLogger()

    @Test
    fun testCreation() {
        logger.info("This is the test for the abstract Spline class.")
        val controlPoints = ArrayList<GLVector>()
        class MockSpline(): Spline(controlPoints, 10) {
            override fun splinePoints(): ArrayList<GLVector> {
                return controlPoints
            }
        }
        val mock = MockSpline()
        assertNotNull(mock)
    }
}