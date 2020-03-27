package graphics.scenery.proteins

import cleargl.GLVector

abstract class Spline(open val controlPoints: List<GLVector>, open val n: Int) {
    abstract fun splinePoints(): ArrayList<GLVector>
}