package graphics.scenery.tests.examples.sketches

import org.joml.*
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.numerics.Random
import graphics.scenery.proteins.CatmullRomSpline
import org.junit.Test

class CatmulTestDetail: SceneryBase("CatmulTestDetail", windowWidth = 1280, windowHeight = 720) {

    override fun init() {

        val node = Node("TestCatmulSpline")

        val c = Cylinder(0.25f, 0.1f, 10)
        c.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
        c.instancedProperties["ModelMatrix"] = {c.model}
        c.material.diffuse = Vector3f(1.0f, 1.0f, 1.0f)
        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))

        val p0 = Vector3f(0.0f, 0.0f, 0.0f)
        val p1 = Vector3f(10f, 30f, 50f)
        val p2 = Vector3f(40f, 60f, 90f)
        val p3 = Vector3f(100f, 80f, 110f)
        val a = ArrayList<Vector3f>()
        a.add(p0)
        a.add(p1)
        a.add(p2)
        a.add(p3)

        val spline = CatmullRomSpline(a)
        val catmulChain = spline.splinePoints()

        val cylinders = catmulChain.map {
            val section = Mesh()
            section.parent = node
            val i = catmulChain.indexOf(it)
            if(i < catmulChain.size) {
                section.orientBetweenPoints(it, catmulChain[i + 1],
                        true, true)
            }
            section.instancedProperties["ModelMatrix1"] = { section.model }
            section
        }
        c.instances.addAll(cylinders)

        node.addChild(c)

        scene.addChild(node)

        val rowSize = 10f


        val lightbox = Box(Vector3f(50.0f, 50.0f, 50.0f), insideNormals = true)
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