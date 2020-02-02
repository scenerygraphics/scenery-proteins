package graphics.scenery.tests.examples.PDB

import cleargl.GLVector
import graphics.scenery.Protein
import graphics.scenery.proteins.CatmulSpline
import org.junit.Test

class CatmulTest {

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
        val c = CatmulSpline(a)
        val x = c.CatMulRomChain()
        print(x)
    }
}