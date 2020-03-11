package graphics.scenery.tests.examples.PDB

import cleargl.GLVector
import graphics.scenery.Protein
import graphics.scenery.proteins.CatmullRomSpline
import org.junit.Test

class CatmullTest {

    @Test
    fun main() {
        val p = Protein.fromID("2zzm")
        val a = ArrayList<GLVector>()
        val struc = p.structure
        struc.chains.forEach {
            it.atomGroups.forEach {
                it.atoms.forEach {
                    if(it.name == "C") {
                        a.add(GLVector(it.x.toFloat(), it.y.toFloat(), it.z.toFloat()))
                    }
                }
            }
        }
        val c = CatmullRomSpline(a)
        val x = c.catMullRomChain()
        print(x)
    }
}