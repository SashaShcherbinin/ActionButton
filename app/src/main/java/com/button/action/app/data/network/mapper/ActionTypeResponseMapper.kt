package com.button.action.app.data.network.mapper

import com.button.action.app.domain.type.ActionType
import javax.inject.Inject

class ActionTypeResponseMapper
@Inject
constructor() {

    fun map(value: String): ActionType {
        return when (value) {
            "animation" -> ActionType.ANIMATION
            "toast" -> ActionType.TOAST
            "call" -> ActionType.CALL
            "notification" -> ActionType.NOTIFICATION
            else -> ActionType.UNKNOWN
        }
    }
}