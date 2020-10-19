package graphics.scenery.proteins

import graphics.scenery.Node
import org.joml.Vector3f

class Rainbow {
    /**
     * Assigns each residue its color- if displaySS is false.
     */
    fun colorVector(subProtein: Node) {
        var childrenSize = 0
        subProtein.children.forEach { ss ->
            ss.children.forEach { partialCurve ->
                partialCurve.children.forEach { _ ->
                    childrenSize++
                }
            }
        }

        val childrenCount = childrenSize
        /*
         Palette Rainbow colors palette has 7 HEX, RGB codes colors:
         HEX: #ff0000 RGB: (255, 0, 0),
         HEX: #ffa500 RGB: (255, 165, 0),
         HEX: #ffff00 RGB: (255, 255, 0),
         HEX: #008000 RGB: (0, 128, 0),
         HEX: #0000ff RGB: (0, 0, 255),
         HEX: #4b0082 RGB: (75, 0, 130),
         HEX: #ee82ee RGB: (238, 130, 238).*

         *see: https://colorswall.com/palette/102/
         */
        val rainbowPaletteNotScaled = listOf(Vector3f(255f, 0f, 0f), Vector3f(255f, 165f, 0f),
                Vector3f(255f, 255f, 0f), Vector3f(0f, 128f, 0f), Vector3f(0f, 0f, 255f),
                Vector3f(75f, 0f, 135f), Vector3f(238f, 130f, 238f))
        val rainbowPalette = rainbowPaletteNotScaled.map { it.mul(1/255f) }
        val sixth = (childrenCount/6)
        val colorList = ArrayList<Vector3f>(childrenCount)
        for(j in 1..6) {
            val dif = Vector3f()
            rainbowPalette[j].sub(rainbowPalette[j-1], dif)
            for(i in 0 until sixth) {
                val color = Vector3f()
                colorList.add(dif.mul(i.toFloat()/sixth.toFloat(), color).add(rainbowPalette[j-1], color))
            }
        }
        for(k in 0 until (childrenCount - colorList.size)) {
            colorList.add(rainbowPalette[6])
        }
        var listIndex = 0
        subProtein.children.forEach { ss ->
            ss.children.forEach { partialCurve ->
                partialCurve.children.forEach {
                    it.material.diffuse = colorList[listIndex]
                    listIndex++
                }
            }
        }

    }
}