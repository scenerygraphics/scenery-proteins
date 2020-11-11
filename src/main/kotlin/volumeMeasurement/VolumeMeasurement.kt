package volumeMeasurement

import graphics.scenery.Mesh

class VolumeMeasurement {
    fun calculateVolume(mesh: Mesh): Float{
        val vertices = mesh.vertices
        if(vertices.hasArray()) {
            val verticesAsList = vertices.array()
            val arraySize = verticesAsList.size
            val numberOfSubVolumes = arraySize % 9
            val subVolumes = ArrayList<Float>(numberOfSubVolumes)
            if (arraySize % 9 == 0) {
                var i = 0
                while (i < arraySize) {
                    val x1 = verticesAsList[i]
                    val y1 = verticesAsList[i + 1]
                    val z1 = verticesAsList[i + 2]
                    val x2 = verticesAsList[i + 3]
                    val y2 = verticesAsList[i + 4]
                    val z2 = verticesAsList[i + 5]
                    val x3 = verticesAsList[i + 6]
                    val y3 = verticesAsList[i + 7]
                    val z3 = verticesAsList[i + 8]
                    val volume = 1 / 6 * (-x3 * y2 * z1 + x2 * y3 * z1 + x3 * y1 * z2 - x1 * y3 * z2 - x2 * y1 * z3 + x1 * y2 * z3)
                    subVolumes.add(volume)
                    i += 9
                }
            }
            return (subVolumes.fold(0f) { acc, next -> acc + next })
        }
        else { return 0f }
    }
}