package sketches

import org.joml.Vector3f
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.controls.behaviours.SelectCommand
import graphics.scenery.numerics.Random
import graphics.scenery.proteins.ruler.CreateCommand
import graphics.scenery.utils.extensions.plus
import org.junit.Test
import kotlin.concurrent.thread

/**
 */
class CreationSketch: SceneryBase("CreationSketch", wantREPL = true) {
    override fun init() {
        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, 512, 512))

        val box = Box(Vector3f(10.0f, 10.0f, 10.0f), insideNormals = true)
        box.material.diffuse = Vector3f(1.0f, 1.0f, 1.0f)
        box.material.cullingMode = Material.CullingMode.Front
        scene.addChild(box)

        val light = PointLight(radius = 15.0f)
        light.position = Vector3f(0.0f, 0.0f, 2.0f)
        light.intensity = 1.0f
        light.emissionColor = Vector3f(1.0f, 1.0f, 1.0f)
        scene.addChild(light)

        val cam: Camera = DetachedHeadCamera()
        with(cam) {
            position = Vector3f(0.0f, 0.0f, 5.0f)
            perspectiveCamera(50.0f, 512, 512)

            scene.addChild(this)
        }
    }
    private val sphere = Icosphere(0.5f, 6)

    override fun inputSetup() {
        super.inputSetup()

        renderer?.let { r ->
            inputHandler?.addBehaviour("create", CreateCommand("create", r, scene,
                    { scene.findObserver()}, sphere))
            inputHandler?.addKeyBinding("create", "double-click button1")
        }
    }

    @Test override fun main() {
        super.main()
    }
}
