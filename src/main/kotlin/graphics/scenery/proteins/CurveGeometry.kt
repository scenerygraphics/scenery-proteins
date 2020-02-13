package graphics.scenery.proteins

import cleargl.GLVector
import graphics.scenery.*
import org.lwjgl.opengl.GL
import java.nio.FloatBuffer
import java.nio.IntBuffer
import kotlin.math.acos
import kotlin.math.sign

class CurveGeometry(val curve: CatmullRomSpline, n: Int = 100): Node("CurveGeometry"), HasGeometry {
    override val vertexSize = 3
    override val texcoordSize = 2
    override var geometryType = GeometryType.TRIANGLES

    override var vertices: FloatBuffer = BufferUtils.allocateFloat(0)
    override var normals: FloatBuffer = BufferUtils.allocateFloat(0)
    override var texcoords: FloatBuffer = BufferUtils.allocateFloat(0)
    override var indices: IntBuffer = BufferUtils.allocateInt(0)

    private val cur = curve.CatMulRomChain(n)

    fun drawSpline(baseShape: (() -> List<GLVector>)) {
        cur.forEach {
            val p = it
            baseShape.invoke().forEach{
                /*Todo! here the vertices need to be added. You have to implement a frenet frame which
                  Todo! rides along the curve. Use this frame to calculate the coordinates for your base
                  Todo! shape at a given point in the curve. For all these new points find an algorithm
                  Todo! which orders and stores them as triangles.  */
            }
        }
    }

    fun getTangent(i: Int): GLVector {
        val s = cur.size
        return when(i) {
            0 -> ((cur[i+1] - cur[i]).normalized)
            s -> ((cur[i] - cur[i-1].normalized))
            else -> ((cur[i + 1] - cur[i - 1]).normalized)
        }
    }


    fun computeFrenetFrames(): Triple<List<GLVector>, List<GLVector>, List<GLVector>> {

        val tangents = ArrayList<GLVector>()
        val normals = ArrayList<GLVector>()
        val binormals = ArrayList<GLVector>()

        //adds all the tangent vectors
        cur.forEach{tangents.add(getTangent(cur.indexOf(it)))}

        //initial normal vector perpendicular to first tangent vector
        val x = tangents[0].x()*-1
        val y = tangents[0].y()*1
        val z = x*x + y*y
        val normal = GLVector(x,y,z)
        normals.add(normal)

        for(i in 0 until cur.size) {
            val b = tangents[i].cross(tangents[i+1])
            if (b.length2() == 0.0f) {
                normals[i+1] = normals[i]
            }
            else {
                val theta = acos(tangents[i].times(tangents[i+1]))
                
            }
        }
        return Triple(tangents, normals, binormals)
    }
}