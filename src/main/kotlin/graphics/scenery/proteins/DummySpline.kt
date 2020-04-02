package graphics.scenery.proteins

import cleargl.GLVector

/**
 * This class is a dummy spline in case one has all the points of a spline and wants to use them in
 * form of a Spline class for instance to draw a Curve.
 *
 * @author Justin Bürger
 */
class DummySpline(override val controlPoints: ArrayList<GLVector>): Spline(controlPoints, n = 100) {

    /**
     * Simply returns the control points as the spline points.
     */
    override fun splinePoints(): ArrayList<GLVector> {
        return controlPoints
    }
}