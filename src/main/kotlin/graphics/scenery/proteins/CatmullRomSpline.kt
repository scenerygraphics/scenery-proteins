package graphics.scenery.proteins

import cleargl.GLVector
import kotlin.math.pow

/**
 * This class offers the logic for creating a CatmuLL Rom Spline. This is essentially
 * a spline going through control points. For more information see:
 * https://en.wikipedia.org/wiki/Centripetal_Catmull–Rom_spline
 * @param controlPoints the list of control points
 * @param alpha determines the kind of Catmull Rom Spline, set in range of 0..1
 */
class CatmullRomSpline(val controlPoints: List<GLVector>, val alpha: Float = 0.5f) {
    
    /**
     * Calculates the parameter t.
     */
    fun getT(ti: Float, Pi: GLVector, Pj: GLVector): Float {
        val exp: Float = (alpha*0.5).toFloat()
        return(((Pj.x()-Pi.x()).pow(2) + (Pj.y()-Pi.y()).pow(2)
                + (Pj.z()-Pi.z()).pow(2)).pow(exp) + ti)
    }

    /**
     * this function returns the spline points between two points. Please note you need four points
     * to have a smooth curve.
     * @param n number of points defining the spline
     */
    fun CatmulRomSpline(P0: GLVector, P1: GLVector, P2: GLVector, P3: GLVector, n: Int = 100): List<GLVector> {

        val curvePoints = ArrayList<GLVector>()

        val t0 = 0.toFloat()
        val t1 = getT(t0, P0, P1)
        val t2 = getT(t1, P1, P2)
        val t3 = getT(t2, P2, P3)

        var t = t1
        while(t<t2) {
            val A1 = P0.times((t1-t)/(t1-t0)) + P1.times((t-t0)/(t1-t0));
            val A2 = P1.times((t2-t)/(t2-t1)) + P2.times((t-t1)/(t2-t1));
            val A3 = P2.times((t3-t)/(t3-t2)) + P3.times((t-t2)/(t3-t2));

            val B1 = A1.times((t2-t)/(t2-t0)) + A2.times((t-t0)/(t2-t0));
            val B2 = A2.times((t3-t)/(t3-t1)) + A3.times((t-t1)/(t3-t1));

            val C = B1.times((t2-t)/(t2-t1)) + B2.times((t-t1)/(t2-t1));
            curvePoints.add(C)

            t += ((t2-t1)/n)
        }

        return curvePoints
    }

    /**
     * Returns the actual curve with all the points.
     * @param n number of points the curve has
     */
    fun CatMulRomChain(n: Int = 100): ArrayList<GLVector> {
        val chainPoints = ArrayList<GLVector>()
        val j = controlPoints.size-4
        for (i in 0..j) {
            val c = CatmulRomSpline(controlPoints[i], controlPoints[i+1],
                    controlPoints[i+2], controlPoints[i+3], n)
            chainPoints.addAll(c)
        }
        return chainPoints
    }

}