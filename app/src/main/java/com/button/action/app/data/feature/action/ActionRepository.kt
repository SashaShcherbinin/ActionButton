package com.button.action.app.data.feature.action

import com.button.action.app.domain.model.Action
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActionRepository
@Inject
constructor(private val actionNetworkStorage: ActionNetworkStorage) {

    fun getActions(): Single<List<Action>> {
        // todo save to memory or database storage
        return actionNetworkStorage.getAction()
    }
}