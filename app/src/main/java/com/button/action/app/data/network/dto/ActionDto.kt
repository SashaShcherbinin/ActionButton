package com.button.action.app.data.network.dto

import com.google.gson.annotations.SerializedName

data class ActionDto(
    @SerializedName("cool_down")
    val coolDown: Long? = null,
    @SerializedName("enabled")
    val enabled: Boolean? = null,
    @SerializedName("priority")
    val priority: Int? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("valid_days")
    val validDays: List<Int?>? = null
)