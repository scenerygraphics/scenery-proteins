package graphics.scenery.proteins

import graphics.scenery.BufferUtils
import graphics.scenery.HasGeometry
import graphics.scenery.Mesh
import graphics.scenery.utils.extensions.minus
import graphics.scenery.utils.extensions.toFloatArray
import org.joml.*
import kotlin.Float.Companion.MIN_VALUE
import kotlin.math.acos

/**
 * Constructs a geometry along the calculates points of a Spline (in this case a Catmull Rom Spline).
 * This class inherits from Node and HasGeometry
 * The number n corresponds to the number of segments you wish to have between your control points.
 *
 * @author  Justin Buerger <burger@mpi-cbg.de>
 * @param [curve] the spline along which the geometry will be rendered
 * @param [baseShape] a lambda which returns all the baseShapes along the curve
 */
class Curve(curve: Spline, baseShape: () -> List<List<Vector3f>>): Mesh("CurveGeometry"), HasGeometry {
    private val chain = curve.splinePoints()
    private val countList = ArrayList<Int>(50).toMutableList()

    /*
     * This function renders the spline.
     * [baseShape] It takes a lambda as a parameter, which is the shape of the
     * curve.
     * If you choose, for example, to have a square as a base shape, your spline will look like
     * a banister.
     */
    init {
        if(chain.isEmpty()) {
            println("The spline provided for the Curve is empty.")
        }
        val bases = computeFrenetFrames(chain as ArrayList<Vector3f>).map { (t, n, b, tr) ->
            val inverseMatrix = Matrix4f(n.x(), b.x(), t.x(), 0f,
                    n.y(), b.y(), t.y(), 0f,
                    n.z(), b.z(), t.z(), 0f,
                    0f, 0f ,0f ,1f).invert()
            val nn = Vector3f(inverseMatrix[0, 0], inverseMatrix[1, 0], inverseMatrix[2, 0]).normalize()
            val nb = Vector3f(inverseMatrix[0, 1],inverseMatrix[1, 1], inverseMatrix[1, 2]).normalize()
            val nt = Vector3f(inverseMatrix[0, 2], inverseMatrix[2, 1], inverseMatrix[2, 2]).normalize()
            Matrix4f(
                    nn.x(), nb.x(), nt.x(), 0f,
                    nn.y(), nb.y(), nt.y(), 0f,
                    nn.z(), nb.z(), nt.z(), 0f,
                    tr.x(), tr.y(), tr.z(), 1f)
        }
        val baseShapes = baseShape.invoke()
        var i = 0
        while (i <= baseShapes.lastIndex) {
            var partialCurveSize = 0
            baseShapes.drop(i).takeWhile { firstShape ->
                val index = ++i
                partialCurveSize++
                if (index < baseShapes.lastIndex) {
                    firstShape.size == baseShapes[index + 1].size
                }
                else {
                    false
                }
            }
            countList.add(partialCurveSize)
        }
        var position = 0
        var lastShapeUnique = false
        if(countList.last() == 1) {
            countList.removeAt(countList.lastIndex)
            lastShapeUnique = true
        }

        countList.forEach {count ->
            val partialCurveGeometry = ArrayList<ArrayList<Vector3f>>(count)
            for(j in 0 until count) {
                val shape = baseShapes[position]
                val shapeVertexList = ArrayList<Vector3f>(shape.size)
                shape.forEach {
                    val vec = Vector3f()
                    shapeVertexList.add(bases[position].transformPosition(it, vec))
                }
                partialCurveGeometry.add(shapeVertexList)
                position++
            }
            val helpPosition = position
            //fill the gaps between the different shapes
            if(helpPosition < bases.lastIndex) {
                val shape = baseShapes[helpPosition]
                val shapeVertexList = ArrayList<Vector3f>(shape.size)
                shape.forEach {
                    val vec = Vector3f()
                    shapeVertexList.add(bases[helpPosition].transformPosition(it, vec))
                }
                partialCurveGeometry.add(shapeVertexList)
            }
            //edge case: the last shape is different from its predecessor
            if(lastShapeUnique && helpPosition == bases.lastIndex) {
                val shape = baseShapes[helpPosition-1]
                val shapeVertexList = ArrayList<Vector3f>(shape.size)
                shape.forEach {
                    val vec = Vector3f()
                    shapeVertexList.add(bases[helpPosition].transformPosition(it, vec))
                }
                partialCurveGeometry.add(shapeVertexList)
            }
            val partialVerticesVector = calculateTriangles(partialCurveGeometry)
            val partialCurve = PartialCurve(partialVerticesVector)
            this.children.add(partialCurve)
        }
    }

    /**
     * This function calculates the tangent at a given index.
     * [i] index of the curve (not the geometry!)
     */
    private fun getTangent(i: Int): Vector3f {
        val s = chain.size
        return when(i) {
            0 -> ((chain[i+1] - chain[i]).normalize())
            (s-2) -> ((chain[i+1] - chain[i]).normalize())
            (s-1) -> ((chain[i] - chain[i-1]).normalize())
            else -> ((chain[i+1] - chain[i-1]).normalize())
        }
    }

    /**
     * Data class to store Frenet frames (wandering coordinate systems), consisting of [tangent], [normal], [binormal]
     */
    data class FrenetFrame(val tangent: Vector3f, var normal: Vector3f, var binormal: Vector3f, val translation: Vector3f)
    /**
     * This function returns the frenet frames along the curve. This is essentially a new
     * coordinate system which represents the form of the curve. For details concerning the
     * calculation see: http://www.cs.indiana.edu/pub/techreports/TR425.pdf
     */
    fun computeFrenetFrames(curve: ArrayList<Vector3f>): List<FrenetFrame> {

        val frenetFrameList = ArrayList<FrenetFrame>(curve.size)

        if(curve.isEmpty()) {
            return frenetFrameList
        }

        //adds all the tangent vectors
        curve.forEachIndexed { index, _ ->
            val frenetFrame = FrenetFrame(getTangent(index), Vector3f(), Vector3f(), curve[index])
            frenetFrameList.add(frenetFrame)
        }
        var min = MIN_VALUE
        val vec = Vector3f()
        val normal = Vector3f()
        if(frenetFrameList[0].tangent.x() <= min) {
            min = frenetFrameList[0].tangent.x()
            normal.set(1f, 0f, 0f)
        }
        if(frenetFrameList[0].tangent.y() <= min) {
            min = frenetFrameList[0].tangent.y()
            normal.set(0f, 1f, 0f)
        }
        if(frenetFrameList[0].tangent.z() <= min) {
            normal.set(0f, 0f, 1f)
        }
        else { normal.set(1f, 0f, 0f) }
        frenetFrameList[0].tangent.cross(normal, vec).normalize()
        frenetFrameList[0].tangent.cross(vec, frenetFrameList[0].normal).normalize()
        frenetFrameList[0].tangent.cross(frenetFrameList[0].normal, frenetFrameList[0].binormal).normalize()

        frenetFrameList.windowed(2,1).forEach { (firstFrame, secondFrame) ->
            val b = Vector3f(firstFrame.tangent).cross(secondFrame.tangent)
            secondFrame.normal = firstFrame.normal.normalize()
            //if there is no substantial difference between two tangent vectors, the frenet frame need not to change
            if (b.length() > MIN_VALUE) {
                val firstNormal = firstFrame.normal
                b.normalize()
                val theta = acos(firstFrame.tangent.dot(secondFrame.tangent).coerceIn(-1f, 1f))
                val q = Quaternionf(AxisAngle4f(theta, b))
                secondFrame.normal = q.transform(Vector3f(firstNormal)).normalize()
            }
            secondFrame.tangent.cross(secondFrame.normal, secondFrame.binormal).normalize()
        }
        return frenetFrameList.filterNot { it.binormal.toFloatArray().all { value -> value.isNaN() } &&
                it.normal.toFloatArray().all{ value -> value.isNaN()}}
    }

    /**
     * This function calculates the triangles for the the rendering. It takes as a parameter
     * the [curveGeometry] List which contains all the baseShapes transformed and translated
     * along the curve.
     */
    private fun calculateTriangles(curveGeometry: List<List<Vector3f>>): ArrayList<Vector3f> {
        val verticesVectors = ArrayList<Vector3f>()
        if(curveGeometry.isEmpty()) {
            return verticesVectors
        }
        //if none of the lists in the curveGeometry differ in size, distinctBy leaves only one element
        if(curveGeometry.distinctBy{ it.size }.size == 1) {
            curveGeometry.dropLast(1).forEachIndexed { shapeIndex, shape ->
                shape.dropLast(1).forEachIndexed { vertexIndex, _ ->

                    verticesVectors.add(curveGeometry[shapeIndex][vertexIndex])
                    verticesVectors.add(curveGeometry[shapeIndex][vertexIndex + 1])
                    verticesVectors.add(curveGeometry[shapeIndex + 1][vertexIndex])

                    verticesVectors.add(curveGeometry[shapeIndex][vertexIndex + 1])
                    verticesVectors.add(curveGeometry[shapeIndex + 1][vertexIndex + 1])
                    verticesVectors.add(curveGeometry[shapeIndex + 1][vertexIndex])
                }
                verticesVectors.add(curveGeometry[shapeIndex][0])
                verticesVectors.add(curveGeometry[shapeIndex + 1][0])
                verticesVectors.add(curveGeometry[shapeIndex + 1][shape.lastIndex])

                verticesVectors.add(curveGeometry[shapeIndex + 1][shape.lastIndex])
                verticesVectors.add(curveGeometry[shapeIndex][shape.lastIndex])
                verticesVectors.add(curveGeometry[shapeIndex][0])
            }
        }
        else {
            throw IllegalArgumentException("The baseShapes must not differ in size!")
        }
        return verticesVectors
    }

    /**
     * Getter for the curve.
     */
    fun getCurve(): ArrayList<Vector3f> {
        return chain as ArrayList<Vector3f>
    }

    class PartialCurve(val verticesVectors: ArrayList<Vector3f>): Mesh("PartialCurve"), HasGeometry {
        init {
            vertices = BufferUtils.allocateFloat(verticesVectors.size * 3)
            verticesVectors.forEach {
                vertices.put(it.toFloatArray())
            }
            vertices.flip()
            texcoords = BufferUtils.allocateFloat(verticesVectors.size * 2)
            recalculateNormals()
        }
    }
}
