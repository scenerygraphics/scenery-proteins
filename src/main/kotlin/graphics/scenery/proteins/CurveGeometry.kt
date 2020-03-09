package graphics.scenery.proteins

import cleargl.GLMatrix
import cleargl.GLVector
import com.jogamp.opengl.math.FloatUtil.makeRotationAxis
import graphics.scenery.*
import java.nio.FloatBuffer
import java.nio.IntBuffer
import kotlin.math.acos
/**
 * Constructs a geometry along the calculates points of a Spline (in this case a Catmull Rom Spline).
 * This class inherits from Node and HasGeometry
 * The number n corresponds to the number of segments you wish to have between you control points.
 * @author  Justin Buerger <burger@mpi-cbg.de>
 */
class CurveGeometry(curve: CatmullRomSpline, n: Int = 100): Node("CurveGeometry"), HasGeometry {
    override val vertexSize = 3
    override val texcoordSize = 2
    override var geometryType = GeometryType.TRIANGLES

    override var vertices: FloatBuffer = BufferUtils.allocateFloat(0)
    override var normals: FloatBuffer = BufferUtils.allocateFloat(0)
    override var texcoords: FloatBuffer = BufferUtils.allocateFloat(0)
    override var indices: IntBuffer = BufferUtils.allocateInt(0)

    private val cur = curve.CatMulRomChain(n)

    /**
     * This function renders the spline.
     * [baseShape] It takes a lambda as a parameter, which is the shape of the
     * curve. If you choose, for example, to have a square as a base shape, your spline will look like
     * a banister. Please not that the base shape needs an equal number of points in each segments but it
     * can very well vary in thickness.
     */
    fun drawSpline(baseShape: (() -> List<GLVector>)) {
        val bases = ArrayList<GLMatrix>()
        computeFrenetFrames().forEach { (t, n, b, tr) ->
            if(n != null && b != null) {
                val basisArray = ArrayList<Float>()
                basisArray.add(n.x())
                basisArray.add(b.x())
                basisArray.add(t.x())
                basisArray.add(0f)
                basisArray.add(n.y())
                basisArray.add(b.y())
                basisArray.add(t.y())
                basisArray.add(0f)
                basisArray.add(n.z())
                basisArray.add(b.z())
                basisArray.add(t.z())
                basisArray.add(0f)
                basisArray.add(0f)
                basisArray.add(0f)
                basisArray.add(0f)
                basisArray.add(1f)
                val array = basisArray.toFloatArray()
                val matrix = GLMatrix(array).inverse
                matrix[3, 0] = tr.x()
                matrix[3, 1] = tr.y()
                matrix[3, 2] = tr.z()
                bases.add(matrix)
            }
        }

        val curveGeometry = ArrayList<ArrayList<GLVector>>()
        bases.forEach {
            val basis = it
            val shape = ArrayList<GLVector>()
            baseShape.invoke().forEach {
                val vector4D = GLVector(it.x(), it.y(), it.z(), 1f)
                val vector = basis.mult(vector4D)
                shape.add(vector.xyz())
            }
            curveGeometry.add(shape)
        }

        val verticesVectors = ArrayList<GLVector>()

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
        vertices = BufferUtils.allocateFloat(verticesVectors.size*3)
        verticesVectors.forEach{
            vertices.put(it.xyz().toFloatArray())
        }
        vertices.flip()
        texcoords = BufferUtils.allocateFloat(verticesVectors.size*2)
        recalculateNormals()
    }

    /**
     * This function calculates the tangent at a given index in the catmull rom curve.
     * [i] index of the curve (not the geometry!)
     */
    private fun getTangent(i: Int): GLVector {
        val s = cur.size
        return when(i) {
            0 -> ((cur[i+1] - cur[i]).normalized)
            (s-2) -> ((cur[i+1] - cur[i]).normalized)
            (s-1) -> ((cur[i] - cur[i-1]).normalized)
            else -> ((cur[i+1] - cur[i-1]).normalized)
        }
    }

    //data class to store Frenet Frames (wandering coordinate systems)
    data class FrenetFrame(val tangent: GLVector, var normal: GLVector?, var bitangent: GLVector?, val translation: GLVector)
    /**
     * This function returns the frenet frames along the curve. This is essentially a new
     * coordinate system which represents the form of the curve. For details concerning the
     * calculation see: http://www.cs.indiana.edu/pub/techreports/TR425.pdf
     */
    fun computeFrenetFrames(): List<FrenetFrame> {

        val frenetFrameList = ArrayList<FrenetFrame>(cur.size)

        //adds all the tangent vectors
        for(i in 0 until cur.size) {
            val frenetFrame = FrenetFrame(getTangent(i), null, null, cur[i])
            frenetFrameList.add(frenetFrame)
        }

        //initial normal vector perpendicular to first tangent vector
        var vec: GLVector
        vec = if(frenetFrameList[0].tangent.x() >= 0.9f || frenetFrameList[0].tangent.z() >= 0.9f) {
            GLVector(0f, 1f, 0f)
        }
        else {
            GLVector(1f, 0f, 0f)
        }

        val normal = frenetFrameList[0].tangent.cross(vec)

        frenetFrameList[0].normal = normal
        frenetFrameList[0].bitangent = frenetFrameList[0].tangent.cross(normal)

        frenetFrameList.windowed(2,1).forEach { (firstFrame, secondFrame) ->
            if (frenetFrameList[0].normal != null && frenetFrameList[0].bitangent != null) {
                val b = firstFrame.tangent.cross(secondFrame.tangent)
                //if there is no substantial difference between two tangent vectors, the frenet frame need not to change
                if (b.length2() < 0.0000001f) {
                    secondFrame.normal = firstFrame.normal
                } else {
                    val x = firstFrame.normal?.x()
                    val y = firstFrame.normal?.y()
                    val z = firstFrame.normal?.z()

                    val theta = acos(firstFrame.tangent.times(secondFrame.tangent))
                    val emptyMatrix = GLMatrix()
                    if (x != null && y != null && z != null) {
                        val normal = firstFrame.normal
                        if(normal != null) {
                            val rotationMatrix = GLMatrix(makeRotationAxis(emptyMatrix.floatArray,
                                    0, theta, x, y, z, normal.toFloatArray()))
                            val normal4D = GLVector(x, y, z, 0f)
                            val rot = rotationMatrix.mult(normal4D)
                            val newNormal = (GLVector(rot.x(), rot.y(), rot.z()))
                            secondFrame.normal = newNormal
                        }
                    }
                }
                secondFrame.bitangent = (secondFrame.tangent.cross(secondFrame.normal))
            }
        }
            return frenetFrameList
    }
}
