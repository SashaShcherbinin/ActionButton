package com.button.action.app.data.network.mapper

import com.button.action.app.domain.type.DayOfWeek
import javax.inject.Inject

class DayOfWeekResponseMapper
@Inject
constructor() {

    fun map(value: Int): DayOfWeek {
        return when (value) {
            0 -> DayOfWeek.MONDAY
            1 -> DayOfWeek.THURSDAY
            2 -> DayOfWeek.WEDNESDAY
            3 -> DayOfWeek.TUESDAY
            4 -> DayOfWeek.FRIDAY
            5 -> DayOfWeek.SATURDAY
            6 -> DayOfWeek.SUNDAY
            else -> error("unsupported day type")
        }
    }
}