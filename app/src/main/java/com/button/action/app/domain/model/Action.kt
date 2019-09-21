package com.button.action.app.domain.model

import com.button.action.app.domain.type.ActionType
import com.button.action.app.domain.type.DayOfWeek

data class Action(
    val actionType: ActionType,
    val enable: Boolean,
    val priority: Int,
    val days: List<DayOfWeek>,
    val couldDown: Long
)