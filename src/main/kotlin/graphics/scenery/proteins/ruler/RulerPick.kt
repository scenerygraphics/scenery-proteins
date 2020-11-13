package graphics.scenery.proteins.ruler

import org.joml.Vector3f
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.controls.behaviours.SelectCommand
import graphics.scenery.numerics.Random
import graphics.scenery.utils.extensions.plus
import org.junit.Test
import kotlin.concurrent.thread

/**
 * Example of how to measure the distance between two nodes.
 *
 * @author Justin Buerger <burger@mpi-cbg.de>
 */
class RulerPick: SceneryBase("RulerPick", wantREPL = true) {

    private var secondNode = false

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

        var lastNode = Node()
        val wiggle: (Scene.RaycastResult, Int, Int) -> Unit = { result, _, _ ->
            result.matches.firstOrNull()?.let { nearest ->
                val originalPosition = Vector3f(nearest.node.position)
                thread {
                    for(i in 0 until 200) {
                        nearest.node.position = originalPosition + Random.random3DVectorFromRange(-0.05f, 0.05f)
                        Thread.sleep(2)
                    }
                }
                if(!secondNode) {
                    lastNode = nearest.node
                }
                else {
                    val position0 = lastNode.position
                    val position1 = nearest.node.position
                    val lastToPresent = Vector3f()
                    logger.info("distance: ${position1.sub(position0, lastToPresent).length()}")
                    val line = Line(simple = true)
                    line.addPoint(position0)
                    line.addPoint(position1)
                    scene.addChild(line)
                }
                secondNode = !secondNode
            }
        }

        renderer?.let { r ->
            inputHandler?.addBehaviour("select", SelectCommand("select", r, scene,
                    { scene.findObserver() }, action = wiggle, debugRaycast = false))
            inputHandler?.addKeyBinding("select", "double-click button1")
        }
    }

    @Test override fun main() {
        super.main()
    }
}