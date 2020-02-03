package graphics.scenery.tests.examples.PDB

import cleargl.GLVector
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.numerics.Random
import graphics.scenery.proteins.SecondaryStructure
import graphics.scenery.proteins.SecondaryStructureSticks
import org.junit.Test

class BackBoneTest: SceneryBase("BackBoneTest", windowWidth = 1280, windowHeight = 720) {
    override fun init() {

        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))

        val rowSize = 10f

        val protein = Protein.fromID("3nir")

        val back = SecondaryStructure(protein)

        scene.addChild(back.secondaryStruc())

        val lightbox = Box(GLVector(50.0f, 50.0f, 50.0f), insideNormals = true)
        lightbox.name = "Lightbox"
        lightbox.material.diffuse = GLVector(0.1f, 0.1f, 0.1f)
        lightbox.material.roughness = 1.0f
        lightbox.material.metallic = 0.0f
        lightbox.material.cullingMode = Material.CullingMode.None
        scene.addChild(lightbox)
        val lights = (0 until 8).map {
            val l = PointLight(radius = 20.0f)
            l.position = GLVector(
                    Random.randomFromRange(-rowSize/2.0f, rowSize/2.0f),
                    Random.randomFromRange(-rowSize/2.0f, rowSize/2.0f),
                    Random.randomFromRange(1.0f, 5.0f)
            )
            l.emissionColor = Random.randomVectorFromRange(3, 0.2f, 0.8f)
            l.intensity = Random.randomFromRange(0.2f, 0.8f)

            lightbox.addChild(l)
            l
        }

        val stageLight = PointLight(radius = 35.0f)
        stageLight.name = "StageLight"
        stageLight.intensity = 0.5f
        stageLight.position = GLVector(0.0f, 0.0f, 5.0f)
        scene.addChild(stageLight)

        val cameraLight = PointLight(radius = 5.0f)
        cameraLight.name = "CameraLight"
        cameraLight.emissionColor = GLVector(1.0f, 1.0f, 0.0f)
        cameraLight.intensity = 0.8f

        val cam: Camera = DetachedHeadCamera()
        with(cam) {
            position = GLVector(0.0f, 0.2f, 12.0f)
            perspectiveCamera(50.0f, windowWidth.toFloat(), windowHeight.toFloat())
            active = true

            scene.addChild(this)
        }

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