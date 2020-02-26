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

    override var vertices: FloatBuffer = BufferUtils.allocateFloat(curve.CatMulRomChain(n).size*9*2*3)
    override var normals: FloatBuffer = BufferUtils.allocateFloat(0)
    override var texcoords: FloatBuffer = BufferUtils.allocateFloat(0)
    override var indices: IntBuffer = BufferUtils.allocateInt(0)

    private val cur = curve.CatMulRomChain(n)

    fun drawSpline(baseShape: (() -> List<GLVector>)): ArrayList<ArrayList<GLVector>> {
        data class TranslationMatrix(val matrix: GLMatrix, val translation: GLVector)
        val bases = ArrayList<TranslationMatrix>()
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
                val matrix = GLMatrix(array)
                val translationMatrix = TranslationMatrix(matrix, tr)
                bases.add(translationMatrix)
            }
        }

        val curveGeometry = ArrayList<ArrayList<GLVector>>()
        bases.forEach { (m,t) ->
            val basis = m.inverse
            val shape = ArrayList<GLVector>()
            baseShape.invoke().forEach {
                val vector4D = GLVector(it.x(), it.y(), it.z(), 0f)
                val vector = basis.mult(vector4D)
                val vertex = GLVector(vector.x()+t.x(), vector.y()+t.y(), vector.z()+t.z())
                shape.add(vertex)
            }
            curveGeometry.add(shape)
        }
        print(curveGeometry)

        val verticesVectors = ArrayList<GLVector>()
        for (j in 0 until curveGeometry.size-1) {
            for(i in 0 until curveGeometry[j].size) {
                if(i != curveGeometry[j].size -1) {

                    vertices.put(curveGeometry[j][i].x())
                    vertices.put(curveGeometry[j][i].y())
                    vertices.put(curveGeometry[j][i].z())
                    verticesVectors.add(curveGeometry[j][i])

                    vertices.put(curveGeometry[j][i+1].x())
                    vertices.put(curveGeometry[j][i+1].y())
                    vertices.put(curveGeometry[j][i+1].z())
                    verticesVectors.add(curveGeometry[j][i+1])

                    vertices.put(curveGeometry[j+1][i].x())
                    vertices.put(curveGeometry[j+1][i].y())
                    vertices.put(curveGeometry[j+1][i].z())
                    verticesVectors.add(curveGeometry[j+1][i])

                    /*
                    vertices.put(curveGeometry[j][i+1].x())
                    vertices.put(curveGeometry[j][i+1].y())
                    vertices.put(curveGeometry[j][i+1].z())
                    verticesVectors.add(curveGeometry[j][i+1])

                    vertices.put(curveGeometry[j+1][i+1].x())
                    vertices.put(curveGeometry[j+1][i+1].y())
                    vertices.put(curveGeometry[j+1][i+1].z())
                    verticesVectors.add(curveGeometry[j+1][i+1])

                    vertices.put(curveGeometry[j+1][i].x())
                    vertices.put(curveGeometry[j+1][i].y())
                    vertices.put(curveGeometry[j+1][i].z())
                    verticesVectors.add(curveGeometry[j+1][i])
                    */
                }
            }
        }
        vertices.flip()
        recalculateNormals()
        return curveGeometry
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


    data class FrenetFrame(val tangent: GLVector, var normal: GLVector?, var bitangent: GLVector?, val translation: GLVector)
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

        for(i in 0 until (frenetFrameList.size-1)) {
            if (frenetFrameList[0].normal != null && frenetFrameList[0].bitangent != null) {
                val b = frenetFrameList[i].tangent.cross(frenetFrameList[i + 1].tangent)
                //if there is no substantial difference between two tangent vectors, the frenet frame need not to change
                if (b.length2() < 0.000001f) {
                    frenetFrameList[i + 1].normal = frenetFrameList[i].normal
                } else {
                    val x = frenetFrameList[i].normal?.x()
                    val y = frenetFrameList[i].normal?.y()
                    val z = frenetFrameList[i].normal?.z()

                    val theta = acos(frenetFrameList[i].tangent.times(frenetFrameList[i + 1].tangent))
                    val emptyMatrix = GLMatrix()
                    if (x != null && y != null && z != null) {
                        val normal = frenetFrameList[i].normal
                        if(normal != null) {
                            val rotationMatrix = GLMatrix(makeRotationAxis(emptyMatrix.floatArray,
                                    0, theta, x, y, z, normal.toFloatArray()))
                            val normal4D = GLVector(x, y, z, 0f)
                            val rot = rotationMatrix.mult(normal4D)
                            val newNormal = (GLVector(rot.x(), rot.y(), rot.z()))
                            frenetFrameList[i+1].normal = newNormal
                        }
                    }
                }
                frenetFrameList[i+1].bitangent = (frenetFrameList[i + 1].tangent.cross(frenetFrameList[i + 1].normal))
            }
        }
            return frenetFrameList
    }
}