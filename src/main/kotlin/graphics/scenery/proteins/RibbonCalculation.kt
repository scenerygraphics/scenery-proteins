package graphics.scenery.proteins

import graphics.scenery.Protein
import graphics.scenery.numerics.Random
import org.biojava.nbio.structure.Atom
import org.biojava.nbio.structure.Group
import org.biojava.nbio.structure.secstruc.SecStrucElement
import org.biojava.nbio.structure.secstruc.SecStrucType
import org.joml.Vector3f

/**
 * A Ribbon Diagram for Proteins. Ribbon models are currently the most common representations of proteins.
 * They draw a Spline approximately along the Backbone and highlight secondary structures like alpha helices
 * with a wider curve along the helix, beta sheets are represented with arrows pointing from the N-terminal
 * to the C-terminal. If you want to know more about the Ribbon diagrams, see the Wikipedia article:
 * https://en.wikipedia.org/wiki/Ribbon_diagram
 * In case you want to dig deeper here is an article of Jane D. Richardson, who developed the Ribbon:
 * https://www.ncbi.nlm.nih.gov/pmc/articles/PMC3535681/ (Studying and Polishing the PDB's Macromolecules)
 *
 * In the code below, we use a Uniform B-Spline to draw the C-alpha trace of the protein. The controlpoints
 * are created via the algorithm of Carlson and Bugg (Algorithm for ribbon models of proteins, Carson et. al)
 * The algorithm roughly works like this:
 * Take a list of coordinates of C-alpha-atoms (Ca) and the coordinates
 * of the Oxygen-atoms (O) of their carbonyl groups. Form the vectors:
 *
 * A(i) = Ca(i)-Ca(i+1)  (i is the index)
 * B(i) = O(i) - Ca(i)
 * C = A X B  (X is the cross product)
 * D = A X C
 *
 * After normalizing C and D, we now form the midpoints between two consecutive C-alpha atoms:
 * P = [Ca(i)-Ca(i+1)]/2
 *
 * Then we scale D by the desired Ribbon width. Finally, we calculate the guide points:
 * P1 = P - D
 * P2 = P + D
 *
 * For the implementation we have consulted KiNG, a visualization software by David C. Richardson et al:
 * https://www.ncbi.nlm.nih.gov/pmc/articles/PMC2788294/
 * (KING (Kinemage, Next Generation): A versatile interactive molecular and scientific visualization program,
 * by Richardson et al.)
 *
 * @author Justin Buerger <burger@mpi-cbg.de>
 * @param [protein] the polypeptide you wish to visualize, stored in the class Protein
 * @param [secStrucs] a list of all the secondary structures (calculated with the DSSP algorithm) in the protein
 */

class RibbonCalculation(val protein: Protein, val secStrucs: List<SecStrucElement>) {

    /**
     * Calculates the GuidePoints from the list of amino acids.
     */
    fun calculateGuidePoints(aminoList: List<Group>): ArrayList<GuidePoint> {
        //To include all points in the visualization, dummy points need to be made.
        //First, a list without these dummy points is calculated.
        val guidePointsWithoutDummy = ArrayList<GuidePoint>(aminoList.size - 1)
        val guidePoints = ArrayList<GuidePoint>(aminoList.size + 4 - 1)
        val maxOffSet = 1.5f
        aminoList.dropLast(1).forEachIndexed { i, _ ->
            val ca1 = aminoList[i].getAtom("CA")
            val ca2 = aminoList[i + 1].getAtom("CA")
            val ca1Vec = ca1.getVector()
            val ca2Vec = ca2.getVector()
            val aVec = Vector3f()
            ca1Vec.sub(ca2Vec, aVec)
            val o = aminoList[i].getAtom("O")
            val bVec = o.getVector()

            val finalPoint = Vector3f()
            ca1Vec.add(ca2Vec, finalPoint)
            finalPoint.mul(0.5f)
            //See Carlson et. al
            val cVec = Vector3f()
            aVec.cross(bVec, cVec)
            cVec.normalize()
            val dVec = Vector3f()
            cVec.cross(aVec, dVec)
            cVec.normalize()
            val widthFactor: Float
            val offset: Float
            /*
             Ribbons widen in areas of high curvature, respectively helices, and
             low curvature, respectively beta sheets. The factors are listed in
             the table* below:

             CA-CA DIST  WIDTH FACTOR    OFFSET FACTOR   NOTE
             ==========  ============    =============   ====
               5.0         1               1               ~limit for curled-up protein
               5.5         1               1               } linear interpolation
               7.0         0               0               } from 1.0 to 0.0
               9.0         0               0               } linear interpolation
               10.5        1               0               } from 1.0 to 0.0
               11.0        1               0               ~limit for extended protein

               *Copyright (C) 2002-2007 Ian W. Davis & Vincent B. Chen. All rights reserved
             */
            when {
                (i >= 1 && i < aminoList.size - 3) -> {
                    val ca0 = aminoList[i - 1].getAtom("CA").getVector()
                    val ca3 = aminoList[i + 2].getAtom("CA").getVector()
                    val ca0Ca3 = Vector3f()
                    ca0.sub(ca3, ca0Ca3)
                    val ca0Ca3Distance = ca0.distance(ca3)
                    when {
                        (ca0Ca3Distance < 7f) -> {
                            widthFactor = java.lang.Float.min(1.5f, 7f - ca0Ca3Distance) / 1.5f
                            offset = widthFactor
                            val ca0Ca3Midpoint = Vector3f()
                            ca0Ca3.mul(0.5f, ca0Ca3Midpoint)
                            val offsetVec = Vector3f()
                            finalPoint.sub(ca0Ca3Midpoint, offsetVec)
                            offsetVec.normalize().mul(offset * maxOffSet)
                            finalPoint.add(offsetVec)
                        }
                        (ca0Ca3Distance > 9f) -> {
                            widthFactor = java.lang.Float.min(1.5f, ca0Ca3Distance - 9f) / 1.5f
                            offset = 0f
                        }
                        else -> {
                            widthFactor = 0f
                            offset = 0f
                        }
                    }
                }
                else -> {
                    widthFactor = 0f
                    offset = 0f
                }
            }
            guidePointsWithoutDummy.add(i, GuidePoint(finalPoint, cVec, dVec, offset, widthFactor,
                    aminoList[i], aminoList[i + 1], SecStrucType.bend, 0))
        }

        guidePointsWithoutDummy.forEachIndexed { index, guide ->
            secStrucs.forEach { ss ->
                if (ss.range.start == guide.nextResidue!!.residueNumber) {
                    for (i in 0 until ss.range.length) {
                        guidePointsWithoutDummy[index + i].type = ss.type
                        guidePointsWithoutDummy[index + i].count = ss.range.length - 1
                    }
                }
            }
        }


        //only assign widthFactor if there are three guide points with one in a row
        guidePointsWithoutDummy.windowed(5, 1) { window ->
            when {
                (window[0].widthFactor == 0f && window[4].widthFactor == 0f) -> {
                    if (window[1].widthFactor == 0f || window[2].widthFactor == 0f || window[3].widthFactor == 0f) {
                        window[1].widthFactor = 0f
                        window[2].widthFactor = 0f
                        window[3].widthFactor = 0f
                    }
                }
            }


        }
        //if there is a width factor is still assigned at the beginning, also assign it to the first point
        if (guidePointsWithoutDummy[1].widthFactor != 0f && guidePointsWithoutDummy[2].widthFactor != 0f &&
                guidePointsWithoutDummy[3].widthFactor != 0f) {
            guidePointsWithoutDummy[0].widthFactor = guidePointsWithoutDummy[1].widthFactor
            guidePointsWithoutDummy[0].type = guidePointsWithoutDummy[0].type
        }
        //if there is a width factor is still assigned at the and, also assign it to the last point
        if (guidePointsWithoutDummy.dropLast(1).last().widthFactor != 0f &&
                guidePointsWithoutDummy.dropLast(2).last().widthFactor != 0f &&
                guidePointsWithoutDummy.dropLast(3).last().widthFactor != 0f) {
            guidePointsWithoutDummy.last().widthFactor = guidePointsWithoutDummy.dropLast(1).last().widthFactor
        }

        //dummy points at the beginning
        fun Vector3f.randomFromVector(): Vector3f {
            return Vector3f(Random.randomFromRange(this.x() - 1f, this.x() + 1f),
                    Random.randomFromRange(this.y() - 1f, this.y() + 1f),
                    Random.randomFromRange(this.z() - 1f, this.z() + 1f))
        }

        val caBegin = aminoList[0].getAtom("CA").getVector()
        //increase the count of the first section because we add one more point
        val count = guidePointsWithoutDummy[0].count
        for (i in 0..count) {
            guidePointsWithoutDummy[i].count++
        }
        val dummyVecBeg = caBegin.randomFromVector()
        guidePoints.add(GuidePoint(dummyVecBeg, guidePointsWithoutDummy[0].cVec, guidePointsWithoutDummy[0].dVec,
                guidePointsWithoutDummy[0].offset, guidePointsWithoutDummy[0].widthFactor, aminoList[0], aminoList[0],
                SecStrucType.bend, 0))
        guidePoints.add(GuidePoint(caBegin, guidePointsWithoutDummy[0].cVec, guidePointsWithoutDummy[0].dVec,
                guidePointsWithoutDummy[0].offset, guidePointsWithoutDummy[0].widthFactor, aminoList[0], aminoList[0],
                SecStrucType.bend, 0))
        //add all guide points from the previous calculation
        guidePoints.addAll(guidePointsWithoutDummy)
        //add dummy points at the end
        val caEnd = aminoList.last().getAtom("CA").getVector()
        guidePoints.add(GuidePoint(caEnd,
                guidePointsWithoutDummy.last().cVec, guidePointsWithoutDummy.last().dVec,
                guidePointsWithoutDummy.last().offset, guidePointsWithoutDummy.last().widthFactor,
                aminoList.last(), aminoList.last(), SecStrucType.bend,
                guidePointsWithoutDummy.last().count))
        val dummyVecEnd = caEnd.randomFromVector()
        guidePoints.add(GuidePoint(dummyVecEnd,
                guidePointsWithoutDummy.last().cVec, guidePointsWithoutDummy.last().dVec,
                guidePointsWithoutDummy.last().offset, guidePointsWithoutDummy.last().widthFactor,
                aminoList.last(), aminoList.last(), SecStrucType.bend,
                0))

        return untwistRibbon(guidePoints)
    }

    /**
     * The calculation of the helices results in twists of the curve. This function
     * takes a list of GuidePoints and untwists them.
     */
    private fun untwistRibbon(guidePoints: ArrayList<GuidePoint>): ArrayList<GuidePoint> {
        guidePoints.forEachIndexed { i, _ ->
            if (i > 0) {
                if (guidePoints[i].dVec.dot(guidePoints[i - 1].dVec) < 0f) {
                    guidePoints[i].dVec.mul(-1f)
                }
            }
        }
        return guidePoints
    }

    /**
     * Inline function to make a Vector out of an atom position, as for now we do not
     * need any information about an atom besides its name and its position
     */
    private fun Atom.getVector(): Vector3f {
        return Vector3f(this.x.toFloat(), this.y.toFloat(), this.z.toFloat())
    }
}
