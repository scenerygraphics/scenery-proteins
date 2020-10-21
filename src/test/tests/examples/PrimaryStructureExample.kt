package examples

import graphics.scenery.*
import graphics.scenery.backends.Renderer
import org.joml.*
import graphics.scenery.numerics.Random
import graphics.scenery.proteins.PrimaryStructure
import graphics.scenery.proteins.Protein
import org.junit.Test

/**
 * Example for a basic representation of a Protein. This example only shows the bonds between atoms- if you would like
 * to visualize the protein with a ribbon diagram, please consult the RainbowRibbonExample.
 *
 * @author  Justin Buerger <burger@mpi-cbg.de>
 */
class PrimaryStructureExample: SceneryBase("LoadingAndShowing", windowWidth = 1280, windowHeight = 720) {

        override fun init() {

            renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))

            val rowSize = 10f

        val protein = Protein.fromID("3nir")
        val primaryStructure = PrimaryStructure(protein)

        primaryStructure.parent = scene
        scene.addChild(primaryStructure)


        val lightbox = Box(Vector3f(25.0f, 25.0f, 25.0f), insideNormals = true)
        lightbox.name = "Lightbox"
        lightbox.material.diffuse = Vector3f(0.1f, 0.1f, 0.1f)
        lightbox.material.roughness = 1.0f
        lightbox.material.metallic = 0.0f
        lightbox.material.cullingMode = Material.CullingMode.None
        scene.addChild(lightbox)
        val lights = (0 until 8).map {
            val l = PointLight(radius = 20.0f)
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

        lights.forEach { lightbox.addChild(it) }


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





