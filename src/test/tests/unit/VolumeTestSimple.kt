package volumeMeasurement

import graphics.scenery.Icosphere
import graphics.scenery.numerics.Random
import graphics.scenery.utils.LazyLogger
import org.junit.Test

class VolumeTestSimple {
    val logger by LazyLogger()
    @Test
    fun testVolume() {
        val s = Icosphere(Random.randomFromRange(0.04f, 0.2f), 2)
        val volume = VolumeMeasurement().calculateVolume(s)
        logger.info("$volume")
    }
}