package graphics.scenery.proteins

import graphics.scenery.Mesh
import graphics.scenery.utils.extensions.minus
import graphics.scenery.utils.extensions.plus
import graphics.scenery.utils.extensions.times
import org.joml.*

class Helix (axis: MathLine, val spline: Spline, baseShape: () -> List<Vector3f>): Mesh("Helix") {
    private val splinePoints = spline.splinePoints()
    private val shape = baseShape.invoke()
    private val axisVector = axis.direction
    private val axisPoint = axis.position


    init {
        val verticesList = ArrayList<List<Vector3f>>(splinePoints.size)
        val sectionVerticesCount = spline.verticesCountPerSection()
        splinePoints.forEach { point ->
            //Calculation of the y-axis which is the vector from spline point which intersects the axis with a 90 degree angle
            val t = (point.sub(axisPoint)).dot(axisVector)/axisVector.length()
            val intermediateAxis = Vector3f()
            intermediateAxis.set(axisVector)
            val plumbLine = Vector3f()
            axisPoint.add(intermediateAxis.mul(t), plumbLine)
            val xAxisI = Vector3f()
            xAxisI.set(axisVector).normalize()
            val yAxisI = Vector3f()
            plumbLine.sub(point, yAxisI).normalize()
            val zAxisI = Vector3f()
            xAxisI.cross(yAxisI, zAxisI).normalize()
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
        verticesList.windowed(sectionVerticesCount, sectionVerticesCount-1) { section ->
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
            val helixSectionVertices = Curve.calculateTriangles(section, i)
            val partialHelix = Curve.PartialCurve(helixSectionVertices)
            this.addChild(partialHelix)
        }
    }
}