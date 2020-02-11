package graphics.scenery.proteins

import cleargl.GLVector
import graphics.scenery.HasGeometry
import graphics.scenery.Mesh

class CurveGeometry(func: (List<GLVector>, Any) -> (List<GLVector>)): Mesh("CurveGeometry"), HasGeometry {

    //Creates a point in 2D with an Index and x,y coordinates
    data class IndexedPoint(val values: Triple<Int, Float, Float>)

    //This resembles a closed Graph in 2D it contains points with an index
    data class Closed2DGraph(val points: List<IndexedPoint>)


    fun calcWith2DGraph(curve: List<GLVector>, graph: Closed2DGraph) {

    }
}