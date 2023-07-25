/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2023 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.formulaeditor.sensor

import android.hardware.SensorManager
import org.catrobat.catroid.formulaeditor.SensorHandler

class SensorCompassDirection: Sensor {
    override fun getSensorValue(): Double {
        return calculateCompassDirection()
    }

    private val radianToDegreeConst = 180f / Math.PI.toFloat()
    var orientations = FloatArray(3)

    private fun calculateCompassDirection(): Double {
        var rotationMatrixOut= FloatArray(16)
        var rotate: Int
        if (!SensorHandler.useRotationVectorFallback) {
            SensorManager.getRotationMatrixFromVector(
                SensorHandler.rotationMatrix,
                SensorHandler.rotationVector
            )
        }
        if (SensorHandler.rotateOrientation().also { rotate = it } == 1) {
            SensorManager.remapCoordinateSystem(
                SensorHandler.rotationMatrix,
                SensorManager.AXIS_Y,
                SensorManager.AXIS_MINUS_X,
                rotationMatrixOut
            )
            SensorManager.getOrientation(rotationMatrixOut, orientations)
        } else if (rotate == -1) {
            SensorManager.remapCoordinateSystem(
                SensorHandler.rotationMatrix,
                SensorManager.AXIS_MINUS_Y,
                SensorManager.AXIS_X,
                rotationMatrixOut
            )
            SensorManager.getOrientation(rotationMatrixOut, orientations)
        } else {
            SensorManager.getOrientation(SensorHandler.rotationMatrix, orientations)
        }
        val sensorValue: Double = orientations[0].toDouble()
        return sensorValue * radianToDegreeConst * -1.0
    }



    companion object {
        @Volatile
        private var instance: SensorCompassDirection? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: SensorCompassDirection().also { instance = it }
            }
    }
}
