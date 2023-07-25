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

package org.catrobat.catroid.test.formulaeditor.sensor

import org.catrobat.catroid.common.LookData
import org.catrobat.catroid.content.Look
import org.catrobat.catroid.content.Sprite
import org.catrobat.catroid.formulaeditor.SensorHandler
import org.catrobat.catroid.formulaeditor.Sensors
import org.junit.Assert
import org.junit.Test
import org.powermock.api.mockito.PowerMockito

class SensorObjectNameSensorsTest {

    @Test
    fun backgroundLookDataNotNotNull() {
        var expectedValue = "look name"
        var mocksprite = PowerMockito.mock(Sprite::class.java)
        var mocklook = PowerMockito.mock(Look::class.java)
        var mockLookList = PowerMockito.mock(List::class.java) as List<LookData>
        var mockLookData = PowerMockito.mock(LookData::class.java)
        mocksprite.look = mocklook
        SensorHandler.currentSprite = mocksprite
        PowerMockito.`when`(mocksprite.lookList).thenReturn(mockLookList)
        PowerMockito.`when`(mocklook.lookData).thenReturn(mockLookData)
        PowerMockito.`when`(mockLookData.name).thenReturn(expectedValue)
        compareToSensor(expectedValue, Sensors.OBJECT_BACKGROUND_NAME)
    }

    @Test
    fun backgroundLookDataNullListNotEmpty() {
        var expectedValue = "look name"
        var mocksprite = PowerMockito.mock(Sprite::class.java)
        var mocklook = PowerMockito.mock(Look::class.java)
        var mockLookList = PowerMockito.mock(List::class.java) as List<LookData>
        var mockLookData = PowerMockito.mock(LookData::class.java)
        mocksprite.look = mocklook
        SensorHandler.currentSprite = mocksprite
        PowerMockito.`when`(mocksprite.lookList).thenReturn(mockLookList)
        PowerMockito.`when`(mocklook.lookData).thenReturn(null)
        PowerMockito.`when`(mockLookList.isEmpty()).thenReturn(false)
        PowerMockito.`when`(mockLookList[0]).thenReturn(mockLookData)
        PowerMockito.`when`(mockLookData.name).thenReturn(expectedValue)
        compareToSensor(expectedValue, Sensors.OBJECT_BACKGROUND_NAME)
    }

    @Test
    fun backgroundLookDataNullListEmpty() {
        var expectedValue = ""
        var mocksprite = PowerMockito.mock(Sprite::class.java)
        var mocklook = PowerMockito.mock(Look::class.java)
        var mockLookList = PowerMockito.mock(List::class.java) as List<LookData>
        var mockLookData = PowerMockito.mock(LookData::class.java)
        mocksprite.look = mocklook
        SensorHandler.currentSprite = mocksprite
        PowerMockito.`when`(mocksprite.lookList).thenReturn(mockLookList)
        PowerMockito.`when`(mocklook.lookData).thenReturn(null)
        PowerMockito.`when`(mockLookList.isEmpty()).thenReturn(true)
        PowerMockito.`when`(mockLookList[0]).thenReturn(mockLookData)
        PowerMockito.`when`(mockLookData.name).thenReturn("look name")
        compareToSensor(expectedValue, Sensors.OBJECT_BACKGROUND_NAME)
    }

    @Test
    fun objectLookDataNotNotNull() {
        var expectedValue = "look name"
        var mocksprite = PowerMockito.mock(Sprite::class.java)
        var mocklook = PowerMockito.mock(Look::class.java)
        var mockLookList = PowerMockito.mock(List::class.java) as List<LookData>
        var mockLookData = PowerMockito.mock(LookData::class.java)
        mocksprite.look = mocklook
        SensorHandler.currentSprite = mocksprite
        PowerMockito.`when`(mocksprite.lookList).thenReturn(mockLookList)
        PowerMockito.`when`(mocklook.lookData).thenReturn(mockLookData)
        PowerMockito.`when`(mockLookData.name).thenReturn(expectedValue)
        compareToSensor(expectedValue, Sensors.OBJECT_LOOK_NAME)
    }

    @Test
    fun objectLookDataNullListNotEmpty() {
        var expectedValue = "look name"
        var mocksprite = PowerMockito.mock(Sprite::class.java)
        var mocklook = PowerMockito.mock(Look::class.java)
        var mockLookList = PowerMockito.mock(List::class.java) as List<LookData>
        var mockLookData = PowerMockito.mock(LookData::class.java)
        mocksprite.look = mocklook
        SensorHandler.currentSprite = mocksprite
        PowerMockito.`when`(mocksprite.lookList).thenReturn(mockLookList)
        PowerMockito.`when`(mocklook.lookData).thenReturn(null)
        PowerMockito.`when`(mockLookList.isEmpty()).thenReturn(false)
        PowerMockito.`when`(mockLookList[0]).thenReturn(mockLookData)
        PowerMockito.`when`(mockLookData.name).thenReturn(expectedValue)
        compareToSensor(expectedValue, Sensors.OBJECT_LOOK_NAME)
    }

    @Test
    fun objectLookDataNullListEmpty() {
        var expectedValue = ""
        var mocksprite = PowerMockito.mock(Sprite::class.java)
        var mocklook = PowerMockito.mock(Look::class.java)
        var mockLookList = PowerMockito.mock(List::class.java) as List<LookData>
        var mockLookData = PowerMockito.mock(LookData::class.java)
        mocksprite.look = mocklook
        SensorHandler.currentSprite = mocksprite
        PowerMockito.`when`(mocksprite.lookList).thenReturn(mockLookList)
        PowerMockito.`when`(mocklook.lookData).thenReturn(null)
        PowerMockito.`when`(mockLookList.isEmpty()).thenReturn(true)
        PowerMockito.`when`(mockLookList[0]).thenReturn(mockLookData)
        PowerMockito.`when`(mockLookData.name).thenReturn("look name")
        compareToSensor(expectedValue, Sensors.OBJECT_LOOK_NAME)
    }

    private fun compareToSensor(value: String, sensor: Sensors) {
        Assert.assertEquals(value, sensor.getSensor().getSensorValue() as String)
    }
}
