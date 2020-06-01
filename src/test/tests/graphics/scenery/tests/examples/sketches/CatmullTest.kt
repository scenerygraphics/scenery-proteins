package graphics.scenery.tests.examples.sketches

import org.joml.*
import graphics.scenery.Protein
import graphics.scenery.proteins.CatmullRomSpline
import org.junit.Test

class CatmullTest {

    @Test
    fun main() {
        val p = Protein.fromID("2zzm")
        val a = ArrayList<Vector3f>()
        val struc = p.structure
        struc.chains.forEach {
            it.atomGroups.forEach {
                it.atoms.forEach {
                    if(it.name == "C") {
                        a.add(Vector3f(it.x.toFloat(), it.y.toFloat(), it.z.toFloat()))
                    }
                }
            }
        }
        val c = CatmullRomSpline(a)
        val x = c.splinePoints()
        print(x)
    }
}