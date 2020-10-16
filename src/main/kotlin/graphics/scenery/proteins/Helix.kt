package graphics.scenery.proteins

import graphics.scenery.Mesh
import org.joml.*

/**
 * This class represents a Helix in 3D space. Currently, it needs a Spline which winds around an axis defined as a line
 * in space. Each spline point is assigned a baseShape. Finally, all the shapes get connected with triangles.
 * [axis] line around which the spline should wind
 * [spline] spline
 *
 * @author  Justin Buerger <burger@mpi-cbg.de>
 */
class Helix (private val axis: MathLine, val spline: Spline, baseShape: () -> List<Vector3f>): Mesh("Helix") {
    private val splinePoints = spline.splinePoints()
    private val shape = baseShape.invoke()
    private val axisVector = axis.direction
    private val axisPoint = axis.position


    init {
        val sectionVerticesCount = spline.verticesCountPerSection()
        val verticesList = calculateVertices()
        //algorithms from the curve class, see Curve (line 219-322)
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

    /**
     * Transformation of the baseShapes along the spline, aligned with the helix axis.
     */
    private fun calculateVertices(): ArrayList<List<Vector3f>> {
        if(axisVector == Vector3f(0f, 0f, 0f)) {
            throw Exception("The direction vector of the axis must no become the null vector.")
        }
        val verticesList = ArrayList<List<Vector3f>>(splinePoints.size)
        splinePoints.forEach { point ->
            /*
            The coordinate systems which walk along the spline are calculated like so:
            The x axis is the axis direction.
            The y axis is the normalized vector from the spline point its neighbor on the axis with least distance
            between them. The y axis vector is then perpendicular to the axis vector, therefore, perpendicular to
            the x axis.*
            The z axis is the normalized cross product between the x and the y axis.

            *Calculate y
            - axis line: l = a + tb (a is the positional vector and b the direction)
            - spline point as: p
            The point on the line with the least distance to the spline point is:
            p' = a + t'b
            with t' = (p-a)*b / |b|^2  (with * being the dot product)
            Then y = (p-p') / |p-p'|
             */
            val iVec = Vector3f()
            val t = (point.sub(axisPoint, iVec)).dot(axisVector)/(axisVector.length()*axisVector.length())
            val intermediateAxis = Vector3f()
            intermediateAxis.set(axisVector)
            val plumbLine = Vector3f()
            axisPoint.add(intermediateAxis.mul(t), plumbLine)
            val xAxisI = Vector3f()
            xAxisI.set(axisVector).normalize()
            val yAxisI = Vector3f()
            point.sub(plumbLine, yAxisI).normalize()
            val zAxisI = Vector3f()
            xAxisI.cross(yAxisI, zAxisI).normalize()
            //point transformation
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
        return verticesList
    }
}