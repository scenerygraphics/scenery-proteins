package graphics.scenery.tests.examples.sketches

import org.joml.*
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.numerics.Random
import graphics.scenery.proteins.Curve
import graphics.scenery.proteins.RibbonCalculation
import org.junit.Test

class FlatRibbonSketch: SceneryBase("FlatRibbonSketch", windowWidth = 1280, windowHeight = 720) {
    override fun init() {

        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))

        val rowSize = 10f

        val protein = Protein.fromID("6jmd")

        val diagram = RibbonCalculation(protein)

        val node = Node("Spheres")

        val chains = protein.structure.chains
        val caCollection = ArrayList<Vector3f>(100)
        chains.forEach{ chain ->
            chain.atomGroups.forEach {group ->
                group.atoms.forEach {
                    if(it.name == "CA") {
                       val caVec = Vector3f(it.x.toFloat(), it.y.toFloat(), it.z.toFloat())
                        caCollection.add(caVec)
                    }
                }
            }
        }


        val sphere = Icosphere(0.5f, 2)
        sphere.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
        sphere.instancedProperties["ModelMatrix"] = {sphere.model}
        sphere.material.diffuse = Vector3f(1.0f, 1.0f, 1.0f)
        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))

        val caPoints = caCollection.map {
            val section = Mesh()
            section.parent = node
            section.position = it
            section.instancedProperties["ModelMatrix1"] = { section.model }
            section
        }
        sphere.instances.addAll(caPoints)

        node.addChild(sphere)
        scene.addChild(node)


        fun triangle(splineVerticesCount: Int): ArrayList<ArrayList<Vector3f>> {
            val shapeList = ArrayList<ArrayList<Vector3f>>(splineVerticesCount)
            for (i in 0 until splineVerticesCount) {
                val list = ArrayList<Vector3f>(3)
                list.add(Vector3f(0.3f, 0.3f, 0f))
                list.add(Vector3f(0.3f, -0.3f, 0f))
                list.add(Vector3f(-0.3f, -0.3f, 0f))
                shapeList.add(list)
            }
            return shapeList
        }
        val curve = Curve(diagram.flatRibbon()) { triangle(diagram.flatRibbon().splinePoints().size) }

        scene.addChild(curve)

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