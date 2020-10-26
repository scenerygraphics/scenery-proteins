package graphics.scenery.proteins.ruler

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
open class CreateCommand @JvmOverloads constructor(protected val name: String,
                                                   protected val renderer: Renderer,
                                                   protected val scene: Scene,
                                                   protected val camera: () -> Camera?,
                                                    protected val meshLambda: () -> Mesh) : ClickBehaviour {
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


    /**
     * This is the action executed upon triggering this action, with [x] and [y] being
     * the screen-space coordinates.
     */
    override fun click(x: Int, y: Int) {
        scene.updateWorld(true)
        mesh.parent = scene
        val position = Vector3f()
        position.set(cam!!.position)
        val z = Vector3f()
        z.set(cam!!.cameraTripod().z)
        mesh.position = position.add(z.normalize().mul(5f))
        scene.addChild(mesh)
    }
}
