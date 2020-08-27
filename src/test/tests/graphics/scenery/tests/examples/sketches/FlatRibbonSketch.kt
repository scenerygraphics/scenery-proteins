package graphics.scenery.tests.examples.sketches

import org.joml.*
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.numerics.Random
import graphics.scenery.proteins.RibbonDiagram
import org.junit.Test

class FlatRibbonSketch: SceneryBase("FlatRibbonSketch", windowWidth = 1280, windowHeight = 720) {
    override fun init() {

        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))

        val rowSize = 10f

        val protein = Protein.fromID("3nir")

        val diagram = RibbonDiagram(protein)

        val ribbon = diagram.ribbon()

        val alphaColour =  Random.random3DVectorFromRange(0f, 1f)
        val betaColour =  Random.random3DVectorFromRange(0f, 1f)
        val coilColour =  Random.random3DVectorFromRange(0f, 1f)
        /*
        ribbon.children.forEach {subProtein ->
            subProtein.children.forEach { ss ->
                when {
                    (ss.name == "alpha") -> {
                        ss.children.forEach {alpha ->
                            alpha.children.forEach {
                                it.material.diffuse.set(alphaColour)
                            }
                        }
                    }
                    (ss.name == "beta") -> {
                        ss.children.forEach {beta ->
                            beta.children.forEach {
                                it.material.diffuse.set(betaColour)
                            }
                        }
                    }
                    (ss.name == "coil") -> {
                        ss.children.forEach {coil ->
                            coil.children.forEach {
                                it.material.diffuse.set(coilColour)
                            }
                        }
                    }
                }
            }
        }

         */

        scene.addChild(ribbon)

        val lightbox = Box(Vector3f(100.0f, 100.0f, 100.0f), insideNormals = true)
        lightbox.name = "Lightbox"
        lightbox.material.diffuse = Vector3f(0.1f, 0.1f, 0.1f)
        lightbox.material.roughness = 1.0f
        lightbox.material.metallic = 0.0f
        lightbox.material.cullingMode = Material.CullingMode.None
        scene.addChild(lightbox)
        val lights = (0 until 8).map {
            val l = PointLight(radius = 80.0f)
            l.position = Vector3f(
                    Random.randomFromRange(-rowSize/2.0f, rowSize/2.0f),
                    Random.randomFromRange(-rowSize/2.0f, rowSize/2.0f),
                    Random.randomFromRange(1.0f, 5.0f)
            )
            l.emissionColor = Random.random3DVectorFromRange( 0.2f, 0.8f)
            l.intensity = Random.randomFromRange(0.2f, 0.8f)

            lightbox.addChild(l)
            l
        }

        val stageLight = PointLight(radius = 35.0f)
        stageLight.name = "StageLight"
        stageLight.intensity = 0.5f
        stageLight.position = Vector3f(0.0f, 0.0f, 5.0f)
        scene.addChild(stageLight)

        val cameraLight = PointLight(radius = 5.0f)
        cameraLight.name = "CameraLight"
        cameraLight.emissionColor = Vector3f(1.0f, 1.0f, 0.0f)
        cameraLight.intensity = 0.8f

        val cam: Camera = DetachedHeadCamera()
        cam.position = Vector3f(0.0f, 0.0f, 15.0f)
        cam.perspectiveCamera(50.0f, windowWidth, windowHeight)
        scene.addChild(cam)

        cam.addChild(cameraLight)
    }

    override fun inputSetup() {
        super.inputSetup()
        setupCameraModeSwitching()
    }

    @Test
    override fun main() {
        super.main()
    }
}