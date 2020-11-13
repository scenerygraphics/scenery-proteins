package sketches

import org.joml.Vector3f
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.proteins.ruler.CreateCommandScreen
import org.junit.Test

/**
 * Sketch for Node-Creation on-click. Press R to create a node at your mouse position.
 *
 * @author Justin Buerger <burger@mpi-cbg.de>
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

    override fun inputSetup() {
        super.inputSetup()

        val sphere = Icosphere(0.1f, 6)
        renderer?.let { r ->
            inputHandler?.addBehaviour("create", CreateCommandScreen("create", r, scene,
                    { scene.findObserver() }) { sphere })
            inputHandler?.addKeyBinding("create", "R")
        }
    }

    @Test override fun main() {
        super.main()
    }
}
