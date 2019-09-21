package com.button.action.app.data.feature.action

import com.button.action.app.data.network.mapper.ActionResponseMapper
import com.button.action.app.data.network.service.ActionService
import com.button.action.app.domain.model.Action
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class ActionNetworkStorage
@Inject
constructor(
    retrofit: Retrofit,
    private val actionResponseMapper: ActionResponseMapper
) {

    private val service: ActionService = retrofit.create(ActionService::class.java)

    fun getAction(): Single<List<Action>> {
        return service.getActions().map { list ->
            list.map { actionResponseMapper.map(it) }
        }
    }
}