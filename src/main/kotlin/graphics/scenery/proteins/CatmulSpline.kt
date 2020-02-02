package graphics.scenery.proteins

import cleargl.GLVector
import kotlin.math.pow

class CatmulSpline(val atomCoordinates: List<GLVector>, val alpha: Float = 0.5f) {

    fun getT(ti: Float, Pi: GLVector, Pj: GLVector): Float {
        val exp: Float = (alpha*0.5).toFloat()
        return(((Pj.x()-Pi.x()).pow(2) + (Pj.y()-Pi.y()).pow(2)
                + (Pj.z()-Pi.z()).pow(2)).pow(exp) + ti)
    }

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

    fun CatMulRomChain(): ArrayList<GLVector> {
        val chainPoints = ArrayList<GLVector>()
        val j = atomCoordinates.size-4
        for (i in 0..j) {
            val c = CatmulRomSpline(atomCoordinates[i], atomCoordinates[i+1],
                    atomCoordinates[i+2], atomCoordinates[i+3])
            chainPoints.addAll(c)
        }
        return chainPoints
    }

}