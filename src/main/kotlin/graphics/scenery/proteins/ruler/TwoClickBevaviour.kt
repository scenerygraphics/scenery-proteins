package graphics.scenery.proteins.ruler

import org.scijava.ui.behaviour.Behaviour

interface TwoClickBevaviour: Behaviour {
    fun firstClick(x: Int, y: Int)
    fun secondClick(x: Int, y: Int)
}