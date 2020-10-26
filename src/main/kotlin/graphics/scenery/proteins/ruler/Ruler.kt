package graphics.scenery.proteins.ruler

import graphics.scenery.Camera
import graphics.scenery.Mesh
import graphics.scenery.Scene
import graphics.scenery.TextBoard
import graphics.scenery.backends.Renderer
import graphics.scenery.utils.LazyLogger
import org.joml.Vector3f
import org.joml.Vector4f
import org.scijava.ui.behaviour.ClickBehaviour
import kotlin.reflect.KProperty

/**
 *
 */
open class Ruler @JvmOverloads constructor(protected val name: String,
                                                   protected val renderer: Renderer,
                                                   protected val scene: Scene,
                                                   protected val camera: () -> Camera?,
                                                   protected val referenceMesh: Mesh,
                                                    protected val mesh: Mesh) : ClickBehaviour {
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


    /**
     * This is the action executed upon triggering this action, with [x] and [y] being
     * the screen-space coordinates.
     */
    override fun click(x: Int, y: Int) {
        scene.updateWorld(true)
        mesh.parent = scene
        val camPosition = Vector3f()
        camPosition.set(cam!!.position)
        val x = Vector3f()
        x.set(cam!!.cameraTripod().x).normalize()
        val y = Vector3f()
        y.set(cam!!.cameraTripod().y).normalize()
        val z = Vector3f()
        z.set(cam!!.cameraTripod().z).normalize()
        val position = Vector3f()
        camPosition.add(z.mul(5f, position), position)
        mesh.position = position
        scene.addChild(mesh)
        val referencePosition = referenceMesh.position
        val length = Vector3f()
        logger.info("This is the distance: ${referencePosition.sub(position, length).length()}")

        val board = TextBoard()
        board.text = "Distance: ${length.length()} units"
        board.name = "TextBoard"
        board.transparent = 0
        board.fontColor = Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        board.backgroundColor = Vector4f(100f, 100f, 100f, 1.0f)
        val boardPosition = Vector3f()
        referencePosition.add(position, boardPosition).mul(0.5f)
        board.position = boardPosition.mul(0.5f)
        board.scale = Vector3f(0.5f, 0.5f, 0.5f)

        scene.addChild(board)
    }
}