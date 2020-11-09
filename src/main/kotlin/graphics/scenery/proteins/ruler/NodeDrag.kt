package graphics.scenery.proteins.ruler

import graphics.scenery.Camera
import graphics.scenery.Cylinder
import graphics.scenery.Node
import graphics.scenery.Scene
import graphics.scenery.utils.LazyLogger
import org.joml.Vector2f
import org.joml.Vector3f
import org.scijava.ui.behaviour.DragBehaviour

class NodeDrag(private val name: String, private val cam: Camera, private val node: Node, private val scene: Scene): DragBehaviour {

    private val line = Cylinder(0.01f, 0.01f, 10)
    private val origin = Vector3f()
    val length = Vector3f()
    private val logger by LazyLogger()

    override fun init(p0: Int, p1: Int) {
        origin.set(getMousePositionIn3D(p0, p1))
        line.position = origin
        line.parent = scene
        scene.addChild(line)
    }

    override fun drag(p0: Int, p1: Int) {
        val position = getMousePositionIn3D(p0, p1)
        line.orientBetweenPoints(origin, position)
    }

    override fun end(p0: Int, p1: Int) {
        val endPosition = getMousePositionIn3D(p0, p1)
        line.orientBetweenPoints(origin, endPosition)
        endPosition.sub(origin, length)
        logger.info("The line is ${length.absolute()}")
    }

    private fun getMousePositionIn3D(p0: Int, p1: Int): Vector3f {
        val width = cam.width
        val height = cam.height
        val posX = (p0 - width / 2.0f) / (width / 2.0f)
        val posY = -1.0f * (p1 - height / 2.0f) / (height / 2.0f)
        val mousePosition = cam.viewportToView(Vector2f(posX, posY))
        val position4D = cam.viewToWorld(mousePosition)
        return Vector3f(position4D.x(), position4D.y(), position4D.z())
    }
}