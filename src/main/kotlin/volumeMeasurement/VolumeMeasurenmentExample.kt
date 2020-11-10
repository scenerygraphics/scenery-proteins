package volumeMeasurement

import org.joml.Vector3f
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.controls.behaviours.SelectCommand
import graphics.scenery.numerics.Random
import org.junit.Test

/**
 * Test for [SelectCommand], produces a lot of (clickable) spheres, that
 * wiggle when selected by double-click.
 *
 * @author Ulrik Günther <hello@ulrik.is>
 */
class VolumeMeasurementExample: SceneryBase("VolumeMeasurementExample", wantREPL = true) {
    private val volumeMeasurer = VolumeMeasurement()
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

        val measureVolume: (Scene.RaycastResult, Int, Int) -> Unit = { result, _, _ ->
            result.matches.firstOrNull()?.let { nearest ->
                if(nearest.node is Mesh) {
                    val mesh = nearest.node as Mesh?
                    val volume =
                    if(mesh != null){ volumeMeasurer.calculateVolume(nearest.node as Mesh) } else { 0f }
                    logger.info("The volume is: $volume")
                }
            }
        }

        renderer?.let { r ->
            inputHandler?.addBehaviour("select", SelectCommand("select", r, scene,
                    { scene.findObserver() }, action = measureVolume, debugRaycast = false))
            inputHandler?.addKeyBinding("select", "double-click button1")
        }
    }

    @Test override fun main() {
        super.main()
    }
}
