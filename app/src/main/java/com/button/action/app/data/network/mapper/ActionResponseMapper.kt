package com.button.action.app.data.network.mapper

import com.button.action.app.data.network.dto.ActionDto
import com.button.action.app.domain.model.Action
import javax.inject.Inject

class ActionResponseMapper
@Inject
constructor(
    private val aciTypeResponseMapper: ActionTypeResponseMapper,
    private val dayOfWeekResponseMapper: DayOfWeekResponseMapper
) {

    fun map(actionDto: ActionDto): Action {
        return Action(
            aciTypeResponseMapper.map(actionDto.type!!),
            actionDto.enabled!!,
            actionDto.priority!!,
            actionDto.validDays!!.map { dayOfWeekResponseMapper.map(it!!) },
            actionDto.coolDown!!
        )
    }
}