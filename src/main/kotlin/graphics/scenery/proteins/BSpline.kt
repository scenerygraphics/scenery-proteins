package graphics.scenery.proteins

import cleargl.GLVector
import java.awt.font.NumericShaper
import java.util.EnumSet.range

class BSpline(val knots: List<GLVector>) {

    fun deBoor(k: Int, u: Int, c: ArrayList<GLVector>, p:Int): ArrayList<GLVector> {
        /*
        Evaluates the B-Spline curve.
        k: Index of the knot-interval which contains x
        u: Position
        c: Array of control points (in this case the atom-positions)
        p: degree of the b spline
         */
        val t = ArrayList<GLVector>()
        //t is the array of interval points (points in between the control points)
        t.add(c[0])
        var i = 1
        while(i < c.size) {
            t.add(c[i])
             i++
        }
        t.add(c.last())

        val d = ArrayList<GLVector>()
        val j: Int
        for (j in 0.. p + 1) {
            d.add(c[j + k - p])
        }
        val r: Int
        for (r in 1.. p + 1) {
            for(j in p downTo r-1) {
                val alpha = (u - t[j+k-p].x()) / (t[j+1+k-r].x() - t[j+k-p].x())
                val beta = (u - t[j +k-p].y()) / (t[j+1+k].y() - t[j+k-p].y())
                val gamma = (u - t[j+k-p].z()) / (t[j+1+k].z() - t[j+k-p].z())
                d[j] = GLVector((((1.0 - alpha) * d[j-1].x() + alpha * d[j].x()).toFloat()),
                        (((1.0- beta)*d[j-1].y() + beta*d[j].y()).toFloat()),
                        (((1.0-gamma) + d[j-1].z() + gamma*d[j].z()).toFloat()))
            }
        }
        print(d)
        return d

    }

}