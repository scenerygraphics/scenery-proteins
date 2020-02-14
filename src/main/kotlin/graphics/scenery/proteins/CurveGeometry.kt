package graphics.scenery.proteins

import cleargl.GLMatrix
import cleargl.GLVector
import com.jogamp.opengl.math.FloatUtil.makeRotationAxis
import graphics.scenery.*
import org.lwjgl.opengl.GL
import java.nio.FloatBuffer
import java.nio.IntBuffer
import kotlin.math.acos
import kotlin.math.sign

class CurveGeometry(curve: CatmullRomSpline, n: Int = 100): Node("CurveGeometry"), HasGeometry {
    override val vertexSize = 3
    override val texcoordSize = 2
    override var geometryType = GeometryType.TRIANGLES

    override var vertices: FloatBuffer = BufferUtils.allocateFloat(curve.CatMulRomChain(n).size*3*2*3)
    override var normals: FloatBuffer = BufferUtils.allocateFloat(0)
    override var texcoords: FloatBuffer = BufferUtils.allocateFloat(0)
    override var indices: IntBuffer = BufferUtils.allocateInt(0)

    private val cur = curve.CatMulRomChain(n)

    fun drawSpline(baseShape: (() -> List<GLVector>)) {
        val bases = ArrayList<GLMatrix>()
        for(i in 0 until cur.size) {
            val basisArray = ArrayList<Float>()
            this.computeFrenetFrames().first[i].toFloatArray().forEach { basisArray.add(it)}
            basisArray.add(0f)
            this.computeFrenetFrames().second[i].toFloatArray().forEach{ basisArray.add(it) }
            basisArray.add(0f)
            this.computeFrenetFrames().third[i].toFloatArray().forEach{ basisArray.add(it)}
            basisArray.add(0f)
            basisArray.add(0f)
            basisArray.add(0f)
            basisArray.add(0f)
            basisArray.add(1f)
            val array = basisArray.toFloatArray()
            val matrix = GLMatrix(array)
            bases.add(matrix)
        }
        val curveGeometry = ArrayList<ArrayList<GLVector>>()
        bases.forEach {
            val basis = it.inverse
            val shape = ArrayList<GLVector>()
            baseShape.invoke().forEach {
                val vector4D = GLVector(it.x(), it.y(), it.z(), 0f)
                val vector = basis.mult(vector4D)
                val vertex = GLVector(vector.x(), vector.y(), vector.z())
                shape.add(vertex)
            }
            curveGeometry.add(shape)
        }

        for (j in 0 until curveGeometry.size-1) {
            for(i in 0 until curveGeometry[j].size) {
                if(i != curveGeometry[j].size -1) {
                    vertices.put(curveGeometry[j][i].x())
                    vertices.put(curveGeometry[j][i].y())
                    vertices.put(curveGeometry[j][i].z())
                    vertices.put(curveGeometry[j+1][i].x())
                    vertices.put(curveGeometry[j+1][i].y())
                    vertices.put(curveGeometry[j+1][i].z())
                    vertices.put(curveGeometry[j][i+1].x())
                    vertices.put(curveGeometry[j][i+1].y())
                    vertices.put(curveGeometry[j][i+1].z())
                    vertices.put(curveGeometry[j][i+1].x())
                    vertices.put(curveGeometry[j][i+1].y())
                    vertices.put(curveGeometry[j][i+1].z())
                    vertices.put(curveGeometry[j+1][i+1].x())
                    vertices.put(curveGeometry[j+1][i+1].y())
                    vertices.put(curveGeometry[j+1][i+1].z())
                    vertices.put(curveGeometry[j+1][i].x())
                    vertices.put(curveGeometry[j+1][i].y())
                    vertices.put(curveGeometry[j+1][i].z())
                }
            }
        }
    }

    fun getTangent(i: Int): GLVector {
        val s = cur.size
        return when(i) {
            0 -> ((cur[i+1] - cur[i]).normalized)
            (s-2) -> ((cur[i+1] - cur[i]).normalized)
            (s-1) -> ((cur[i] - cur[i-1]).normalized)
            else -> ((cur[i+1] - cur[i-1]).normalized)
        }
    }


    fun computeFrenetFrames(): Triple<List<GLVector>, List<GLVector>, List<GLVector>> {

        val tangents = ArrayList<GLVector>()
        val normals = ArrayList<GLVector>()
        val binormals = ArrayList<GLVector>()

        //adds all the tangent vectors
        for(i in 0 until cur.size) {
            tangents.add(getTangent(i))
        }

        //initial normal vector perpendicular to first tangent vector
        var vec: GLVector
        vec = if(tangents[0].x() >= 0.9f || tangents[0].z() >= 0.9f) {
            GLVector(0f, 1f, 0f)
        }
        else {
            GLVector(1f, 0f, 0f)
        }

        val normal = tangents[0].cross(vec)

        normals.add(normal)
        binormals.add(tangents[0].cross(normal))

        for(i in 0 until (tangents.size-1)) {
            val b = tangents[i].cross(tangents[i+1])
            if (b.length2() < 0.000001f) {
                normals.add(normals[i])
            }
            else {
                val x = normals[i].x()
                val y = normals[i].y()
                val z = normals[i].z()
                val theta = acos(tangents[i].times(tangents[i+1]))
                val emptyMatrix = GLMatrix()
                val rotationMatrix = GLMatrix(makeRotationAxis(emptyMatrix.floatArray,
                        0, theta, x,y,z, normals[i].toFloatArray()))
                val normal4D = GLVector(normals[i].x(), normals[i].y(), normals[i].z(), 0f)
                val rot = rotationMatrix.mult(normal4D)
                normals.add(GLVector(rot.x(), rot.y(), rot.z()))
            }
            binormals.add(tangents[i+1].cross(normals[i+1]))
        }
        return Triple(tangents, normals, binormals)
    }
}