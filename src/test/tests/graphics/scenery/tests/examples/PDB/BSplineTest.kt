package graphics.scenery.tests.examples.PDB

import cleargl.GLVector
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.numerics.Random
import graphics.scenery.proteins.UniformBSpline
import org.junit.Test

class BSplineTest: SceneryBase("BSplineTest", windowWidth = 1280, windowHeight = 720) {

    override fun init() {

        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))

        val rowSize = 10f

        val points = ArrayList<GLVector>()
        points.add(GLVector(-8f, -9f, -9f))
        points.add(GLVector(-7f, -5f, -7f))
        points.add(GLVector(-5f, -5f, -5f))
        points.add(GLVector(-4f, -2f, -3f))
        points.add(GLVector(-2f, -3f, -4f))
        points.add(GLVector(-1f, -1f, -1f))
        points.add(GLVector(0f, 0f, 0f))
        points.add(GLVector(2f, 1f, 0f))

        val spline = UniformBSpline(points, 10)
        val curve = spline.splinePoints()

        val node = Node("TestCatmulSpline")


        val sphere = Icosphere(0.5f, 2)
        sphere.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
        sphere.instancedProperties["ModelMatrix"] = {sphere.model}
        sphere.material.diffuse = GLVector(1.0f, 1.0f, 1.0f)
        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))

        val controlpoints = points.map {
            val section = Mesh()
            section.parent = node
            section.position = it
            section.instancedProperties["ModelMatrix1"] = { section.model }
            section
        }
        sphere.instances.addAll(controlpoints)

        node.addChild(sphere)

        val c = Icosphere(0.1f, 2)
        c.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
        c.instancedProperties["ModelMatrix"] = {c.model}
        c.material.diffuse = GLVector(1.0f, 1.0f, 1.0f)
        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))


        val spheres = curve.map {
            val section = Mesh()
            section.parent = node
            section.position = it
            section.instancedProperties["ModelMatrix1"] = { section.model }
            section
        }
        c.instances.addAll(spheres)

        node.addChild(c)

        scene.addChild(node)

        val lightbox = Box(GLVector(25.0f, 25.0f, 25.0f), insideNormals = true)
        lightbox.name = "Lightbox"
        lightbox.material.diffuse = GLVector(0.1f, 0.1f, 0.1f)
        lightbox.material.roughness = 1.0f
        lightbox.material.metallic = 0.0f
        lightbox.material.cullingMode = Material.CullingMode.None
        scene.addChild(lightbox)
        val lights = (0 until 8).map {
            val l = PointLight(radius = 20.0f)
            l.position = GLVector(
                    Random.randomFromRange(-rowSize / 2.0f, rowSize / 2.0f),
                    Random.randomFromRange(-rowSize / 2.0f, rowSize / 2.0f),
                    Random.randomFromRange(1.0f, 5.0f)
            )
            l.emissionColor = Random.randomVectorFromRange(3, 0.2f, 0.8f)
            l.intensity = Random.randomFromRange(0.2f, 0.8f)

            lightbox.addChild(l)
            l
        }

        val stageLight = PointLight(radius = 10.0f)
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
            perspectiveCamera(25.0f, windowWidth.toFloat(), windowHeight.toFloat())
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
