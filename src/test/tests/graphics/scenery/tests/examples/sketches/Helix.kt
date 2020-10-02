package graphics.scenery.tests.examples.sketches

import graphics.scenery.Mesh
import graphics.scenery.proteins.Curve
import graphics.scenery.proteins.Spline
import graphics.scenery.utils.extensions.minus
import graphics.scenery.utils.extensions.plus
import graphics.scenery.utils.extensions.times
import org.joml.*

class Helix (private val axisVector: Vector3f, val axisPoint: Vector3f, val spline: Spline, baseShape: () -> List<Vector3f>): Mesh("Helix") {
    val splinePoints = spline.splinePoints()
    val shape = baseShape.invoke()

    init {
        val verticesList = ArrayList<List<Vector3f>>(splinePoints.size)
        val sectionVerticesCount = spline.verticesCountPerSection()
        splinePoints.forEach { point ->
            //Calculation of the y-axis which is the vector from spline point which intersects the axis with a 90 degree angle
            val t = (point.minus(axisPoint)).mul(axisVector)/axisVector.absolute()
            val plumbLine = axisPoint.plus(axisVector.times(t))
            val xAxisI = axisVector.normalize()
            val yAxisI = plumbLine.minus(point).normalize()
            val zAxisI = xAxisI.cross(yAxisI).normalize()
            val inversionMatrix = Matrix3f(xAxisI, yAxisI, zAxisI).invert()
            val xAxis = Vector3f()
            inversionMatrix.getColumn(0, xAxis).normalize()
            val yAxis = Vector3f()
            inversionMatrix.getColumn(1, yAxis).normalize()
            val zAxis = Vector3f()
            inversionMatrix.getColumn(2, zAxis).normalize()
            val transformMatrix = Matrix4f(xAxis.x(), yAxis.x(), zAxis.x(), 0f,
                                            xAxis.y(), yAxis.y(), zAxis.y(), 0f,
                                            xAxis.z(), yAxis.z(), zAxis.z(), 0f,
                                            point.x(), point.y(), point.z(), 1f)
            verticesList.add(shape.map { shapePoint ->
                val transformedPoint = Vector3f()
                transformMatrix.transformPosition(shapePoint, transformedPoint)
            })
        }
        verticesList.windowed(sectionVerticesCount, sectionVerticesCount) { section ->
            val i = when {
                section.contains(verticesList.first()) -> {
                    0
                }
                section.contains(verticesList.last()) -> {
                    1
                }
                else -> {
                    2
                }
            }
            val helixSectionVertices = Curve.VerticesCalculation.calculateTriangles(section, i)
            val partialHelix = Curve.PartialCurve(helixSectionVertices)
            this.addChild(partialHelix)
        }
    }
}