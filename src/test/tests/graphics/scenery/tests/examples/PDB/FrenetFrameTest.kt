package graphics.scenery.tests.examples.PDB

import cleargl.GLVector
import graphics.scenery.proteins.CatmullRomSpline
import graphics.scenery.proteins.Curve
import org.junit.Test

class FrenetFrameTest {
    @Test
    fun main () {
        val points = ArrayList<GLVector>()
        points.add(GLVector(0f, 0f, 0f))
        points.add(GLVector(2f, 1f, 0f))
        points.add(GLVector(1f, 3f, 5f))
        points.add(GLVector(5f, 5f, 1f))
        points.add(GLVector(5f, 10f, 1f))
        points.add(GLVector(7f, 11f, 5f))
        points.add(GLVector(9f, 7f, 3f))
        points.add(GLVector(12f, 8f, -1f))

        val catmullRom = CatmullRomSpline(points)
        val curve = Curve(catmullRom, 4)
    }
}