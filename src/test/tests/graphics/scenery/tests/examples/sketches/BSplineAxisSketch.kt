package graphics.scenery.tests.examples.sketches

import org.joml.*
import graphics.scenery.*
import graphics.scenery.proteins.*
import graphics.scenery.backends.Renderer
import graphics.scenery.numerics.Random
import graphics.scenery.proteins.CatmullRomSpline
import graphics.scenery.proteins.Curve
import graphics.scenery.proteins.GuidePoint
import graphics.scenery.proteins.RibbonDiagram
import graphics.scenery.proteins.UniformBSpline
import org.biojava.nbio.structure.Atom
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.secstruc.SecStrucCalc
import org.biojava.nbio.structure.secstruc.SecStrucElement
import org.biojava.nbio.structure.secstruc.SecStrucTools
import org.biojava.nbio.structure.secstruc.SecStrucType
import org.junit.Test

class BSplineAxisSketch: SceneryBase("BSplineAxisSketch", windowWidth = 1280, windowHeight = 720) {
    override fun init() {

        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))

        val rowSize = 10f

        val protein = Protein.fromID("5mbn")

        val chainList = ArrayList<ArrayList<Group>>()
        protein.structure.chains.forEach { chain ->
            if (chain.isProtein) {
                val aminoList = ArrayList<Group>(chain.atomGroups.size)
                chain.atomGroups.forEach { group ->
                    if (group.hasAminoAtoms()) {
                        aminoList.add(group)
                    }
                }
                chainList.add(aminoList)
            }
        }

        fun dssp(): List<SecStrucElement> {
            //see: https://github.com/biojava/biojava-tutorial/blob/master/structure/secstruc.md
            val ssc = SecStrucCalc()
            ssc.calculate(protein.structure, true)
            return SecStrucTools.getSecStrucElements(protein.structure)
        }

        fun getCount(guidePointList: List<GuidePoint>): Int {
            var count = 0
            guidePointList.forEachIndexed { index, guide ->
                if (index < guidePointList.lastIndex) {
                    val nextGuide = guidePointList[index + 1]
                    //Secondary structures which are not sheets or helices are summarized
                    if (guide.type == SecStrucType.helix4 && nextGuide.type == SecStrucType.helix4 ||
                            guide.type.isBetaStrand && nextGuide.type.isBetaStrand ||
                            (guide.type != SecStrucType.helix4 && nextGuide.type != SecStrucType.helix4
                                    && !guide.type.isBetaStrand && !nextGuide.type.isBetaStrand)){
                        count++
                    } else {
                        return count
                    }
                }
            }
            return count
        }

        fun Atom.getVector(): Vector3f {
            return Vector3f(this.x.toFloat(), this.y.toFloat(), this.z.toFloat())
        }


        chainList.forEach { chain ->
            val caList = ArrayList<Vector3f>()
            val guidePoints = RibbonDiagram.GuidePointCalculation.calculateGuidePoints(chain, dssp())
            var guidePointCount = 0
            while(guidePointCount < guidePoints.lastIndex) {
                val guideIndex = guidePointCount
                val guide = guidePoints[guidePointCount]
                val count = getCount(guidePoints.drop(guidePointCount))
                guidePointCount++
                if(guide.type == SecStrucType.helix4) {
                    for (i in 0 until count) {
                        caList.add(guidePoints[guideIndex+i].nextResidue?.getAtom("CA")!!.getVector())
                    }
                }
            }
            val spline = UniformBSpline(caList)
            val octagon = ArrayList<Vector3f>(8)
            val sin45 = kotlin.math.sqrt(2f) / 40f
            octagon.add(Vector3f(0.05f, 0f, 0f))
            octagon.add(Vector3f(sin45, sin45, 0f))
            octagon.add(Vector3f(0f, 0.05f, 0f))
            octagon.add(Vector3f(-sin45, sin45, 0f))
            octagon.add(Vector3f(-0.05f, 0f, 0f))
            octagon.add(Vector3f(-sin45, -sin45, 0f))
            octagon.add(Vector3f(0f, -0.05f, 0f))
            octagon.add(Vector3f(sin45, -sin45, 0f))
            val baseShape = ArrayList<List<Vector3f>>()
            fun base(): ArrayList<List<Vector3f>> {
                caList.forEach { _ -> baseShape.add(octagon) }
                return baseShape
            }
            val curve = Curve(spline) { base() }
            scene.addChild(curve)
            val spline1 = CatmullRomSpline(guidePoints.map{ it.finalPoint})
            fun base1(): ArrayList<List<Vector3f>> {
                guidePoints.forEach { _ -> baseShape.add(octagon) }
                return baseShape
            }
            val curve1 = Curve(spline1)  { base1() }
            scene.addChild(curve1)
        }

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