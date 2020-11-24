package graphics.scenery.proteins

import org.joml.*

/**
 * This class is a dummy spline in case one has all the points of a spline and wants to use them in
 * form of a Spline class for instance to draw a Curve.
 *
 * @author  Justin Buerger <burger@mpi-cbg.de>
 */
class DummySpline(private val controlPoints: ArrayList<Vector3f>, val sectionVerticesCount: Int): Spline {

    /**
     * Simply returns the control points as the spline points.
     */
    override fun splinePoints(): ArrayList<Vector3f> {
        return controlPoints
    }

    override fun controlPoints(): List<Vector3f> {
        return controlPoints
    }

    override fun verticesCountPerSection(): Int {
        return sectionVerticesCount
    }
}
