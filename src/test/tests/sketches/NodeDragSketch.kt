package sketches

import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.controls.behaviours.SelectCommand
import graphics.scenery.numerics.Random
import graphics.scenery.proteins.ruler.NodeDrag
import graphics.scenery.utils.extensions.plus
import org.joml.Vector3f
import org.junit.Test
import kotlin.concurrent.thread

class NodeDragSketch: SceneryBase("NodeDragSketch", wantREPL = true) {
    override fun init() {
        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, 512, 512))

        for(i in 0 until 200) {
            val s = Icosphere(Random.randomFromRange(0.04f, 0.2f), 2)
            s.position = Random.random3DVectorFromRange(-5.0f, 5.0f)
            scene.addChild(s)
        }

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

        val wiggle: (Scene.RaycastResult, Int, Int) -> Unit = { result, _, _ ->
            result.matches.firstOrNull()?.let { nearest ->
                val originalPosition = Vector3f(nearest.node.position)
                thread {
                    for(i in 0 until 200) {
                        nearest.node.position = originalPosition + Random.random3DVectorFromRange(-0.05f, 0.05f)
                        Thread.sleep(2)
                    }
                }
            }
        }

        renderer?.let { r ->
            inputHandler?.addBehaviour("drag", NodeDrag("drag", r, scene,
                    { scene.findObserver() }, debugRaycast = false))
            inputHandler?.addKeyBinding("select", "R")
        }
    }

    @Test
    override fun main() {
        super.main()
    }
}