package graphics.scenery.proteins.ruler

import org.scijava.ui.behaviour.ScrollBehaviour
import graphics.scenery.Camera
import graphics.scenery.Mesh
import graphics.scenery.Scene
import graphics.scenery.backends.Renderer
import graphics.scenery.utils.LazyLogger
import org.joml.Vector2f
import org.joml.Vector3f
import org.scijava.ui.behaviour.ClickBehaviour
import kotlin.reflect.KProperty

/**
 *
 */
open class CreateCommandRayCast constructor(protected val name: String,
                                           protected val renderer: Renderer,
                                           protected val scene: Scene,
                                           protected val camera: () -> Camera?,
                                           protected val isHorizontal: Boolean,
                                           meshLambda: () -> Mesh) : ScrollBehaviour {
    protected val logger by LazyLogger()

    protected val cam: Camera? by CameraDelegate()

    protected val mesh = meshLambda.invoke()

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


    override fun scroll(p0: Double, p1: Boolean, p2: Int, p3: Int) {
        val width = cam!!.width
        val height = cam!!.height
        val posX = (p2 - width / 2.0f) / (width / 2.0f)
        val posY = -1.0f * (p3 - height / 2.0f) / (height / 2.0f)
        scene.updateWorld(true)
        mesh.parent = scene
        val mousePosition = cam!!.viewportToView(Vector2f(posX, posY))
        val position4D = cam!!.viewToWorld(mousePosition)
        val position = Vector3f(position4D.x(), position4D.y(), position4D.z())
        mesh.position = position
        scene.addChild(mesh)
        val viewAndWheel = Vector3f()
        if(cam != null) {
            cam!!.target.mul(p0.toFloat(), viewAndWheel)
        }
        val newPosition = Vector3f()
        position.add(viewAndWheel, newPosition)
        mesh.position = newPosition
    }
}