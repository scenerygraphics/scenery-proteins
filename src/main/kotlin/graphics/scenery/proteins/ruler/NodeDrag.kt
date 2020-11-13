package graphics.scenery.proteins.ruler

import graphics.scenery.BoundingGrid
import graphics.scenery.Camera
import graphics.scenery.Mesh
import graphics.scenery.Scene
import graphics.scenery.backends.Renderer
import graphics.scenery.numerics.Random
import graphics.scenery.utils.LazyLogger
import graphics.scenery.utils.extensions.plus
import org.joml.Vector2f
import org.joml.Vector3f
import org.scijava.ui.behaviour.DragBehaviour
import kotlin.concurrent.thread
import kotlin.reflect.KProperty

class NodeDrag @JvmOverloads constructor(protected val name: String,
                                                   protected val renderer: Renderer,
                                                   protected val scene: Scene,
                                                   protected val camera: () -> Camera?,
                                                   protected var debugRaycast: Boolean = false,
                                                   var ignoredObjects: List<Class<*>> = listOf<Class<*>>(BoundingGrid::class.java), ) : DragBehaviour {
    protected val logger by LazyLogger()

    protected val cam: Camera? by CameraDelegate()

    /** Camera delegate class, converting lambdas to Cameras. */
    protected inner class CameraDelegate {
        /** Returns the [graphics.scenery.Camera] resulting from the evaluation of [camera] */
        operator fun getValue(thisRef: Any?, property: KProperty<*>): Camera? {
            return camera.invoke()
        }

        /** Setting the value is not supported */
        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Camera?) {
            throw UnsupportedOperationException()
        }
    }

    lateinit var mesh: Mesh

    override fun init(p0: Int, p1: Int) {
        cam?.let { cam ->
            val result = cam.getNodesForScreenSpacePosition(p0, p1, ignoredObjects, debugRaycast)
            result.matches.firstOrNull()?.let { nearest ->
                if(nearest.node is Mesh) {
                    mesh = nearest.node as Mesh
                }
            }
        }
    }


    override fun drag(p0: Int, p1: Int) {
        mesh.position.set(getMousePositionIn3D(p0, p1))
    }

    override fun end(p0: Int, p1: Int) {
        mesh.position.set (getMousePositionIn3D(p0, p1))
    }

    /** Get the position of your mouse in 3D world coordinates*/
    private fun getMousePositionIn3D(p0: Int, p1: Int): Vector3f {
        val width = cam!!.width
        val height = cam!!.height
        val posX = (p0 - width / 2.0f) / (width / 2.0f)
        val posY = -1.0f * (p1 - height / 2.0f) / (height / 2.0f)
        val mousePosition = cam!!.viewportToView(Vector2f(posX, posY))
        val position4D = cam!!.viewToWorld(mousePosition)
        return Vector3f(position4D.x(), position4D.y(), position4D.z())
    }
}