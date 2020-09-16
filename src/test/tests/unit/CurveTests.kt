package unit

import org.joml.*
import graphics.scenery.numerics.Random
import graphics.scenery.proteins.CatmullRomSpline
import graphics.scenery.proteins.Curve
import graphics.scenery.proteins.UniformBSpline
import graphics.scenery.utils.LazyLogger
import org.junit.Test
import org.lwjgl.BufferUtils
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * This is the test class for the [Curve]
 *
 * @author Justin Bürger, burger@mpi-cbg.com
 */
class CurveTests {
    private val logger by LazyLogger()

    /**
     * Tests if the vectors are normalized and if the bitangent and normal vector are becoming null.
     */
    @Test
    fun testCurve() {
        logger.info("This is the test for the Curve.")
        val point1 = Random.random3DVectorFromRange( -30f, -10f)
        val point2 = Random.random3DVectorFromRange( -9f, 20f)
        val point3 = Random.random3DVectorFromRange( 21f, 30f)
        val point4 = Random.random3DVectorFromRange( 31f, 100f)

        val controlPoints = arrayListOf(point1, point2, point3, point4)

        val curve = CatmullRomSpline(controlPoints)

        fun triangle(splineVerticesCount: Int): ArrayList<ArrayList<Vector3f>> {
            val shapeList = ArrayList<ArrayList<Vector3f>>(splineVerticesCount)
            for (i in 0 until splineVerticesCount) {
                val list = ArrayList<Vector3f>()
                list.add(Vector3f(0.3f, 0.3f, 0f))
                list.add(Vector3f(0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, -0.3f, 0f))
                shapeList.add(list)
            }
            return shapeList
        }

        val geometry = Curve(curve) { triangle(curve.splinePoints().size) }
        val frenetFrames = geometry.computeFrenetFrames(geometry.getCurve())

        assertEquals(curve.splinePoints(), geometry.getCurve())
        assertNotNull(frenetFrames.forEach { it.normal })
        assertNotNull(frenetFrames.forEach{ it.binormal })
        assertEquals(frenetFrames.filter { it.binormal.length() < 1.001f && it.binormal.length() > 0.999f },
                frenetFrames)
        assertEquals(frenetFrames.filter { it.normal.length() < 1.001f && it.normal.length() > 0.999f },
                frenetFrames)
        assertEquals(frenetFrames.filter { it.tangent.length() < 1.001f && it.tangent.length() > 0.999f },
                frenetFrames)
    }

    /**
     * Tests if the number of subCurves is correct.
     */
    @Test
    fun testNumberChildren() {
        logger.info("Tests if the number of subCurves is correct.")
        val point1 = Random.random3DVectorFromRange(-30f, -10f)
        val point2 = Random.random3DVectorFromRange(-9f, 20f)
        val point3 = Random.random3DVectorFromRange(21f, 30f)
        val point4 = Random.random3DVectorFromRange(31f, 100f)

        val controlPoints = arrayListOf(point1, point2, point3, point4)

        val spline = UniformBSpline(controlPoints)

        fun shapeGenerator(splineVerticesCount: Int): ArrayList<ArrayList<Vector3f>> {
            val shapeList = ArrayList<ArrayList<Vector3f>>(splineVerticesCount)
            val splineVerticesCountThird = splineVerticesCount / 3
            val splineVerticesCountTwoThirds = splineVerticesCount * 2 / 3
            for (i in 0 until splineVerticesCountThird) {
                val list = ArrayList<Vector3f>()
                list.add(Vector3f(0.3f, 0.3f, 0f))
                list.add(Vector3f(0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, -0.3f, 0f))
                shapeList.add(list)
            }
            for (i in splineVerticesCountThird until splineVerticesCountTwoThirds) {
                val list = ArrayList<Vector3f>()
                list.add(Vector3f(0.3f, 0.3f, 0f))
                list.add(Vector3f(0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, 0.3f, 0f))
                shapeList.add(list)
            }
            for (i in splineVerticesCountTwoThirds until splineVerticesCount) {
                val list = ArrayList<Vector3f>()
                list.add(Vector3f(0.3f, 0.3f, 0f))
                list.add(Vector3f(0.3f, -0.3f, 0f))
                list.add(Vector3f(0f, -0.5f, 0f))
                list.add(Vector3f(-0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, 0.3f, 0f))
                list.add(Vector3f(0f, 0.5f, 0f))
                shapeList.add(list)
            }
            return shapeList
        }
        val curve = Curve(spline) { shapeGenerator(spline.splinePoints().size) }
        assertTrue { curve.children.size == 3 }
    }
    /**
     * Verifies the curve fails when the there are more baseShapes than splinePoints or vice versa.
     */
    @Test
    fun testTooMuchShapeOrPoints() {
        logger.info("Verifies the curve fails when the there are more baseShapes than splinePoints or vice versa.")
        val point1 = Random.random3DVectorFromRange( -30f, -10f)
        val point2 = Random.random3DVectorFromRange( -9f, 20f)
        val point3 = Random.random3DVectorFromRange( 21f, 30f)
        val point4 = Random.random3DVectorFromRange( 31f, 100f)
        val point5 = Random.random3DVectorFromRange(101f, 122f)

        val controlPoints = arrayListOf(point1, point2, point3, point4)

        val spline = CatmullRomSpline(controlPoints)

        val controlPoints2 = arrayListOf(point1, point2, point3, point4, point5)

        val spline2 = CatmullRomSpline(controlPoints2)

        fun shapeGenerator(splineVerticesCount: Int): ArrayList<ArrayList<Vector3f>> {
            val shapeList = ArrayList<ArrayList<Vector3f>>(splineVerticesCount)
            val splineVerticesCountThird = splineVerticesCount / 3
            val splineVerticesCountTwoThirds = splineVerticesCount * 2 / 3
            for (i in 0 until splineVerticesCountThird) {
                val list = ArrayList<Vector3f>()
                list.add(Vector3f(0.3f, 0.3f, 0f))
                list.add(Vector3f(0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, -0.3f, 0f))
                shapeList.add(list)
            }
            for (i in splineVerticesCountThird until splineVerticesCountTwoThirds) {
                val list = ArrayList<Vector3f>()
                list.add(Vector3f(0.3f, 0.3f, 0f))
                list.add(Vector3f(0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, 0.3f, 0f))
                shapeList.add(list)
            }
            for (i in splineVerticesCountTwoThirds..splineVerticesCount) {
                val list = ArrayList<Vector3f>()
                list.add(Vector3f(0.3f, 0.3f, 0f))
                list.add(Vector3f(0.3f, -0.3f, 0f))
                list.add(Vector3f(0f, -0.5f, 0f))
                list.add(Vector3f(-0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, 0.3f, 0f))
                list.add(Vector3f(0f, 0.5f, 0f))
                shapeList.add(list)
            }
            return shapeList
        }
        assertFails { Curve(spline) { shapeGenerator(spline.splinePoints().size) } }
        assertFails { Curve(spline2) { shapeGenerator(spline2.splinePoints().size) } }
    }

    /**
     * Tests if the curve works properly even with an empty spline.
     */
    @Test
    fun testEmptySpline() {
        logger.info("Tests the curve with an empty spline")
        val emptyList = ArrayList<Vector3f>()
        val spline = UniformBSpline(emptyList)
        fun triangle(splineVerticesCount: Int): ArrayList<ArrayList<Vector3f>> {
            val shapeList = ArrayList<ArrayList<Vector3f>>(splineVerticesCount)
            for (i in 0 until splineVerticesCount) {
                val list = ArrayList<Vector3f>()
                list.add(Vector3f(0.3f, 0.3f, 0f))
                list.add(Vector3f(0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, -0.3f, 0f))
                shapeList.add(list)
            }
            return shapeList
        }
        val emptyFloatBuffer = BufferUtils.createFloatBuffer(0)
        val curve = Curve(spline) { triangle(spline.controlPoints().size) }
        assertEquals(curve.vertices, emptyFloatBuffer)
    }
}
