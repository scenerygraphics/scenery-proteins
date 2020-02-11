package graphics.scenery.proteins

import cleargl.GLVector
import graphics.scenery.*
import org.lwjgl.opengl.GL
import java.nio.FloatBuffer
import java.nio.IntBuffer

class CurveGeometry(val curve: List<GLVector>): Node("CurveGeometry"), HasGeometry {
    override val vertexSize = 3
    override val texcoordSize = 2
    override var geometryType = GeometryType.TRIANGLES

    override var vertices: FloatBuffer = BufferUtils.allocateFloat(0)
    override var normals: FloatBuffer = BufferUtils.allocateFloat(0)
    override var texcoords: FloatBuffer = BufferUtils.allocateFloat(0)
    override var indices: IntBuffer = BufferUtils.allocateInt(0)

    fun drawSpline(baseShape: (() -> List<GLVector>)) {
        curve.forEach {
            val p = it
            baseShape.invoke().forEach{
                /*Todo! here the vertices need to be added. You have to implement a frenet frame which
                  Todo! rides along the curve. Use this frame to calculate the coordinates for your base
                  Todo! shape at a given point in the curve. For all these new points find an algorithm
                  Todo! which orders and stores them as triangles.  */
            }
        }
    }
}