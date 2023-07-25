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

import org.catrobat.catroid.formulaeditor.Sensors
import org.catrobat.catroid.formulaeditor.sensor.SensorDateDay
import org.catrobat.catroid.formulaeditor.sensor.SensorDateMonth
import org.catrobat.catroid.formulaeditor.sensor.SensorDateWeekday
import org.catrobat.catroid.formulaeditor.sensor.SensorDateYear
import org.catrobat.catroid.formulaeditor.sensor.SensorTimeHour
import org.catrobat.catroid.formulaeditor.sensor.SensorTimeMinute
import org.catrobat.catroid.formulaeditor.sensor.SensorTimeSecond
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import java.util.Calendar

@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(Parameterized::class)
@PrepareForTest(SensorTimeHour::class, SensorTimeMinute::class, SensorTimeSecond::class,
                SensorDateDay::class, SensorDateMonth::class, SensorDateYear::class,
                SensorDateWeekday::class)
class SensorCalendarSensorsTest (
    private val name: String,
    private val sensor: Sensors,
    private val calendarValue: Int,
    private val calendarReturnValue: Int,
    private val expectedValue: Int) {

    @Before
    fun setup() {
        mockCalendar = Mockito.mock(Calendar::class.java)
        mockStatic(Calendar::class.java)
        PowerMockito.`when`(Calendar.getInstance()).thenReturn(mockCalendar)
        PowerMockito.`when`(mockCalendar[calendarValue]).thenReturn(calendarReturnValue)
    }

    @Test
    fun calendarSensorsTest() {
        compareToSensor(expectedValue, sensor)
    }

    private fun compareToSensor(value: Int, sensor: Sensors) {
        Assert.assertEquals(value.toDouble(), sensor.getSensor().getSensorValue() as Double, DELTA)
    }

    companion object {
        private const val DELTA = 0.01

        lateinit var mockCalendar: Calendar

        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun parameters() = listOf(
            arrayOf("timeHour", Sensors.TIME_HOUR, Calendar.HOUR_OF_DAY, 25, 25),
            arrayOf("timeMinute", Sensors.TIME_MINUTE, Calendar.MINUTE, 95, 95),
            arrayOf("timeSecond", Sensors.TIME_SECOND, Calendar.SECOND, 65, 65),
            arrayOf("dateDay", Sensors.DATE_DAY, Calendar.DAY_OF_MONTH, 32, 32),
            arrayOf("dateMonthJan", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.JANUARY, 1),
            arrayOf("dateMonthFeb", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.FEBRUARY, 2),
            arrayOf("dateMonthMar", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.MARCH, 3),
            arrayOf("dateMonthApr", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.APRIL, 4),
            arrayOf("dateMonthMay", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.MAY, 5),
            arrayOf("dateMonthJun", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.JUNE, 6),
            arrayOf("dateMonthJul", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.JULY, 7),
            arrayOf("dateMonthAug", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.AUGUST, 8),
            arrayOf("dateMonthSep", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.SEPTEMBER, 9),
            arrayOf("dateMonthOct", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.OCTOBER, 10),
            arrayOf("dateMonthNov", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.NOVEMBER, 11),
            arrayOf("dateMonthDec", Sensors.DATE_MONTH, Calendar.MONTH, Calendar.DECEMBER, 12),
            arrayOf("dateYear", Sensors.DATE_YEAR, Calendar.YEAR, 1066, 1066),
            arrayOf("dateYear", Sensors.DATE_YEAR, Calendar.YEAR, 1066, 1066),
            arrayOf("dateWeekDayMon", Sensors.DATE_WEEKDAY, Calendar.DAY_OF_WEEK, Calendar
                .MONDAY, 1),
            arrayOf("dateWeekDayTue", Sensors.DATE_WEEKDAY, Calendar.DAY_OF_WEEK, Calendar
                .TUESDAY, 2),
            arrayOf("dateWeekDayWed", Sensors.DATE_WEEKDAY, Calendar.DAY_OF_WEEK, Calendar
                .WEDNESDAY, 3),
            arrayOf("dateWeekDayThu", Sensors.DATE_WEEKDAY, Calendar.DAY_OF_WEEK, Calendar
                .THURSDAY, 4),
            arrayOf("dateWeekDayFri", Sensors.DATE_WEEKDAY, Calendar.DAY_OF_WEEK, Calendar
                .FRIDAY, 5),
            arrayOf("dateWeekDaySat", Sensors.DATE_WEEKDAY, Calendar.DAY_OF_WEEK, Calendar
                .SATURDAY, 6),
            arrayOf("dateWeekDaySun", Sensors.DATE_WEEKDAY, Calendar.DAY_OF_WEEK, Calendar
                .SUNDAY, 7),
        )
    }
}
