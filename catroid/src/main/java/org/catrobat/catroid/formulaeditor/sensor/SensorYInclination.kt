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
import kotlin.math.abs
import kotlin.math.acos

class SensorYInclination: Sensor {
    override fun getSensorValue(): Double {
        return calculateYInclination()
    }

    private val radianToDegreeConst = 180f / Math.PI.toFloat()
    var orientations = FloatArray(3)

    private fun calculateYInclination(): Double {
        var rotationMatrixOut= FloatArray(16)
        val sensorValue: Double
        var rotate: Int
        return if (SensorHandler.useRotationVectorFallback) {
            val rawInclinationY: Float
            if (SensorHandler.rotateOrientation().also { rotate = it } != 0) {
                rawInclinationY =
                    radianToDegreeConst * acos((SensorHandler.accelerationXYZ[0] * rotate).toDouble())
                        .toFloat()
            } else {
                rawInclinationY = radianToDegreeConst * acos(
                    SensorHandler.accelerationXYZ[1].toDouble()
                ).toFloat()
            }
            var correctedInclinationY = 0f
            if (rawInclinationY in 90.0..180.0) {
                correctedInclinationY = if (SensorHandler.signAccelerationZ > 0) {
                    -(rawInclinationY - 90)
                } else {
                    -(180 + (90 - rawInclinationY))
                }
            } else if (rawInclinationY >= 0 && rawInclinationY < 90) {
                correctedInclinationY = if (SensorHandler.signAccelerationZ > 0) {
                    90 - rawInclinationY
                } else {
                    90 + rawInclinationY
                }
            }
            correctedInclinationY.toDouble()
        } else {
            orientations = FloatArray(3)
            SensorManager.getRotationMatrixFromVector(
                SensorHandler.rotationMatrix,
                SensorHandler.rotationVector
            )
            if (SensorHandler.rotateOrientation() == 1) {
                SensorManager.remapCoordinateSystem(
                    SensorHandler.rotationMatrix,
                    SensorManager.AXIS_Y,
                    SensorManager.AXIS_MINUS_X,
                    rotationMatrixOut
                )
                SensorManager.getOrientation(rotationMatrixOut, orientations)
            } else if (SensorHandler.rotateOrientation() == -1) {
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
            val xInclinationUsedToExtendRangeOfRoll =
                orientations[2] * radianToDegreeConst * -1f
            sensorValue = orientations[1].toDouble()
            if (abs(xInclinationUsedToExtendRangeOfRoll) <= 90f) {
                sensorValue * radianToDegreeConst * -1f
            } else {
                val uncorrectedYInclination =
                    sensorValue.toFloat() * radianToDegreeConst * -1f
                if (uncorrectedYInclination > 0f) {
                    180.0 - uncorrectedYInclination
                } else {
                    -180.0 - uncorrectedYInclination
                }
            }
        }
    }


    companion object {
        @Volatile
        private var instance: SensorYInclination? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: SensorYInclination().also { instance = it }
            }
    }
}
