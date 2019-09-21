package com.button.action.app.data.network.service

import com.button.action.app.data.network.dto.ActionDto
import io.reactivex.Single
import retrofit2.http.GET

interface ActionService {

    @GET("https://s3-us-west-2.amazonaws.com/androidexam/butto_to_action_config.json")
    fun getActions():
            Single<List<ActionDto>>
}