package graphics.scenery.proteins

import cleargl.GLMatrix
import cleargl.GLVector

/**
 * This class generates a smooth curve within the convex hull of the control points. Note that the spline
 * does not necessarily pass through the control points. If you need a curve, which actually contains the
 * control points, use the Catmull Rom Spline instead. However, if this is not required and you prefer a
 * smoother curve over the exact one, this is the spline to draw.
 * The control points are represented by a List of Vectors ([controlpoints]) and the number of points the
 * curve shall contain is [n], which is the hundred times the number of controlpoints by default.
 */
class UniformBSpline(val controlpoints: ArrayList<GLVector>, val n: Int = controlpoints.size*100) {

    val basisMatrix = GLMatrix(floatArrayOf(
            0f, 0f, 0f, 1/6f,
            1/6f, 1/2f, 1/2f, -1/2f,
            2/3f, 0f, -1f, 1/2f,
            1/6f, -1/2f, 1/2f, -1/6f
    ))

    fun partialSpline(p1: GLVector, p2: GLVector, p3: GLVector, p4: GLVector): ArrayList<GLVector> {
        val pointMatrix = GLMatrix(floatArrayOf(
                p1.x(), p2.x(), p3.x(), p4.x(),
                p1.y(), p2.y(), p3.y(), p4.y(),
                p1.z(), p2.z(), p3.z(), p4.z(),
                1f, 1f, 1f, 1f))
        val intermediateMatrix = GLMatrix(pointMatrix.mult(basisMatrix.floatArray))
        val partialSpline = ArrayList<GLVector>(n)

        for(i in 1..n) {
            val t = (i/n).toFloat()
            val tVector = GLVector(1f, t, t*t, t*t*t)
            partialSpline.add(intermediateMatrix.mult(tVector.xyz()))
        }

        return(partialSpline)
    }

    fun bSplineCurvePoints(): ArrayList<GLVector> {
        val curvepoints = ArrayList<GLVector>((controlpoints.size-3)*n +1)
        controlpoints.dropLast(3).forEachIndexed{ index, _ ->
            val spline = partialSpline(controlpoints[index], controlpoints[index +1],
            controlpoints[index+2], controlpoints[index+3])
            curvepoints.addAll(spline)
        }
        return curvepoints
    }
}