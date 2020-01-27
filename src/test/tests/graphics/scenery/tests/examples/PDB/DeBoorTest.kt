package graphics.scenery.tests.examples.PDB

import cleargl.GLVector
import graphics.scenery.Protein
import graphics.scenery.proteins.BSpline
import graphics.scenery.proteins.SecondaryStructure
import org.junit.Test

class DeBoorTest {

    @Test
    fun main() {
        val p = Protein.fromID("3nir")
        val struc = p.structure
        //Atom coordinates of the backbone
        val a = ArrayList<GLVector>()
        struc.chains.forEach{
            it.atomGroups.forEach{ it ->
                it.atoms.forEach{
                    if(it.name == "C" || it.name == "CA" || it.name == "N") {
                        val p = GLVector(it.x.toFloat(), it.y.toFloat(), it.z.toFloat())
                        a.add(p)
                    }
                }
            }
        }
        val spline = BSpline(a)
        spline.deBoor(5, 5, a, 3)
    }
}