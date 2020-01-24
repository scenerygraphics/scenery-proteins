package graphics.scenery.proteins

import cleargl.GLVector
import java.util.EnumSet.range

class BSpline(val knots: List<GLVector>) {

    fun deBoor(k: Int, x: Int, c: ArrayList<GLVector>, p:Int) {
        val t = ArrayList<GLVector>()
        t.add(c[0])
        var i = 1
        while(i < c.size) {
            t.add(c[i])
             i++
        }
        t.add(c.last())

    }

}