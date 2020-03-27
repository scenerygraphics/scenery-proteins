package graphics.scenery.proteins

import cleargl.GLMatrix
import cleargl.GLVector

/**
 * This class generates a smooth curve within the convex hull of the control points. Note that the spline
 * does not necessarily pass through the control points. If you need a curve, which actually contains the
 * control points, use the Catmull Rom Spline instead. However, if this is not required and you prefer a
 * smoother curve over the exact one, this is the spline to draw.
 * The control points are represented by a List of Vectors ([controlPoints]) and the number of points the
 * curve shall contain is [n], which is the hundred times the number of controlpoints by default.
 */
class UniformBSpline(val controlPoints: ArrayList<GLVector>, val n: Int = controlPoints.size*100) {

    private val array = floatArrayOf(
            0f, 0f, 0f, 1f,
            1f, 3f, 3f, -3f,
            4f, 0f, -6f, 3f,
            1f, -3f, 3f, -1f)
    private val basisMatrix = GLMatrix(array)

    private fun partialSpline(p1: GLVector, p2: GLVector, p3: GLVector, p4: GLVector, j: Int): ArrayList<GLVector> {
        val pointMatrix = GLMatrix(floatArrayOf(
                p1.x(), p2.x(), p3.x(), p4.x(),
                p1.y(), p2.y(), p3.y(), p4.y(),
                p1.z(), p2.z(), p3.z(), p4.z(),
                1f, 1f, 1f, 1f))
        pointMatrix.mult(basisMatrix)
        val partialSpline = ArrayList<GLVector>(n)

        for(i in 1..n) {
            val t = i/n.toFloat()
            val tVector = GLVector(1f, t, t*t, t*t*t)
            partialSpline.add(pointMatrix.mult(tVector).times(1/6f).xyz())
        }

        return(partialSpline)
    }

    fun bSplineCurvePoints(): ArrayList<GLVector> {
        val curvepoints = ArrayList<GLVector>((controlPoints.size-3)*n +1)
        controlPoints.dropLast(3).forEachIndexed{ index, _ ->
            val spline = partialSpline(controlPoints[index], controlPoints[index +1],
            controlPoints[index+2], controlPoints[index+3])
            curvepoints.addAll(spline)
        }
        println(curvepoints)
        return curvepoints
    }
}