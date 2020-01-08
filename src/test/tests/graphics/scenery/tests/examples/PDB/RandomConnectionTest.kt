package graphics.scenery.tests.examples.PDB

import cleargl.GLVector
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import graphics.scenery.numerics.Random
import org.junit.Test
import org.biojava.nbio.structure.io.*
import org.biojava.nbio.structure.*
import org.biojava.nbio.structure.StructureTools.getAllAtomArray

/**
 * Simple example to make the atoms of a PDB-File visible.
 *
 * This example will draw all the atoms from a PDB-File according to their position.
 *
 * @author Justin Buerger <burger@mpi-cbg.de>
 */

class RandomConnectionTest: SceneryBase("LoadingAndShowing", windowWidth = 1280, windowHeight = 720) {

    override fun init() {

        renderer = hub.add(Renderer.createRenderer(hub, applicationName, scene, windowWidth, windowHeight))

        val rowSize = 10f


       
        //loads the pdb-files
        val filename = "C:\\Users\\justi\\Desktop\\bsa.pdb"
        val pdbfilereader = PDBFileReader()
        val struc: Structure = pdbfilereader.getStructure(filename)


        val newStruc: Structure = pdbfilereader.getStructureById("4hhb")

        val atoms: Array<Atom> = getAllAtomArray(newStruc)

        val atomMasters = HashMap<Element, Node>()
        Element.values().forEach {
            val s = Icosphere(0.05f, 2)
            s.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
            s.instancedProperties["ModelMatrix"] = { s.world }
            atomMasters[it] = s
        }

        atoms.forEach {
            val s = Node()
            s.position = (GLVector(it.x.toFloat(), it.y.toFloat(), it.z.toFloat()))
            s.instancedProperties["ModelMatrix"] = { s.world }

            when(it.element) {
                Element.H -> {
                    s.material.diffuse = GLVector(1.0f, 1.0f, 1.0f)
                }
                Element.C -> {
                    s.material.diffuse = GLVector(0.0f, 0.0f, 0.0f)
                }
                Element.N -> {
                    s.material.diffuse = GLVector(0.0f, 0.0f, 1.0f)
                }
                Element.O -> {
                    s.material.diffuse = GLVector(1.0f, 0.0f, 0.0f)
                }
                else -> {
                    s.material.diffuse = GLVector(1.0f, 0.2f, 0.8f)
                }
            }

            val master = atomMasters[it.element]
            master?.instances?.add(s)
        }

        logger.info("Atom count: ${atoms.size}")

        atomMasters.filter { it.value.instances.isNotEmpty() }
            .forEach { scene.addChild(it.value) }


        val c = Cylinder(0.025f, 1.0f, 10)
        c.material = ShaderMaterial.fromFiles("DefaultDeferredInstanced.vert", "DefaultDeferred.frag")
        c.instancedProperties["ModelMatrix1"] = { c.model }
        c.material.diffuse = GLVector(1.0f, 1.0f, 1.0f)
        scene.addChild(c)

        val protein = Node("protein")

        val bonds: List<Bond> = atoms.filter{it.bonds != null}.flatMap { it.bonds }

        val randomBonds = atoms.map { BondImpl(it, atoms.random(), 1) }
        val cylinders = randomBonds.map {
            val bond = Mesh()
            bond.parent = protein
            val atomA = it.atomA
            val atomB = it.atomB
            bond.orientBetweenPoints(GLVector(atomA.x.toFloat(), atomA.y.toFloat(), atomA.z.toFloat()),
                GLVector(atomB.x.toFloat(), atomB.y.toFloat(), atomB.z.toFloat()), true, true)
            bond.instancedProperties["ModelMatrix1"] = { bond.model }
            bond
        }
        c.instances.addAll(cylinders)

        scene.addChild(protein)


        //spheres.forEach { scene.addChild(it) }
        /*
        val bonds: List<Bond> = atoms.filter{it.bonds != null}.flatMap { it.bonds }

        /* this would remove the redundant bonds from the list
        for (bond in bonds) {
            for (bond2 in bonds) {
                if (bond.atomA == bond2.atomB && bond.atomB == bond2.atomA) {
                    this.bonds.remove(bond2)
                }
            }
        }
        */

        val cylinders = bonds.map {

            val atomA = it.atomA
            val atomB = it.atomB
            val c = Cylinder(0.01f, 0.1f, 1)
            c.orientBetweenPoints(GLVector(atomA.x.toFloat(), atomA.y.toFloat(), atomA.z.toFloat()),
                       GLVector(atomB.x.toFloat(), atomB.y.toFloat(), atomB.z.toFloat()), true, true)
            c.material.diffuse = GLVector(0.5f, 0.5f, 0.5f)
            c
        }

        //cylinders.filter{it.position == it.position}.forEach { scene.addChild(it) }
        */
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

    @Test override fun main() {
        super.main()
    }
}
