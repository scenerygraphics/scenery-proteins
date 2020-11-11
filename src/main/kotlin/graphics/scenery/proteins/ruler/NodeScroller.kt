package graphics.scenery.proteins.ruler

import graphics.scenery.Camera
import graphics.scenery.Mesh
import graphics.scenery.Scene
import graphics.scenery.utils.LazyLogger
import org.joml.Vector3f
import org.scijava.ui.behaviour.ScrollBehaviour
import kotlin.reflect.KProperty

class NodeScroller(protected val name: String,
                   protected val scene: Scene,
                   protected val camera: () -> Camera?,
                   meshLambda: () -> Mesh): ScrollBehaviour {
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
        val viewAndWheel = Vector3f()
        print(cam!!.target)
        if(cam != null) {
            print(cam!!.target)
            cam!!.target.mul(p0.toFloat(), viewAndWheel)
        }
        mesh.position.add(viewAndWheel)
    }
}