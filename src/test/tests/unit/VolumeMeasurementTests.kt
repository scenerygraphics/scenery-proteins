package unit

import graphics.scenery.*
import graphics.scenery.Icosphere
import org.junit.Test
import kotlin.test.assertEquals
import volumeMeasurement.VolumeMeasurement

/**
 * Very basic test for the volume measurer.
 *
 * @author  Justin Buerger <burger@mpi-cbg.de>
 */
class VolumeMeasurementTests {

    @Test
    fun testVolumeSphere() {
        val s = Icosphere(2f, 2)
        val volume = VolumeMeasurement().calculateVolume(s)
        assertEquals(volume, 32.376359045505524)
    }
    @Test
    fun testVolumeCylinder() {
        val c = Cylinder(2f, 10f, 5)
        val volume = VolumeMeasurement().calculateVolume(c)
        assertEquals(volume, 63.40377712249756)
    }
}
