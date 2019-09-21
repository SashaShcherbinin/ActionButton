package com.button.action.app.data.feature.history

import com.button.action.app.domain.type.ActionType
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepository
@Inject
constructor() {

    // TODO save to a permanent data base
    private val dataBase = HashMap<ActionType, Long>()

    fun getSavedTime(actionType: ActionType): Single<Long> {
        return Single.just(dataBase[actionType] ?: Long.MIN_VALUE)
    }

    fun getSavedTimeInfo(): Observable<Map<ActionType, Long>> {
        return Observable.just(dataBase)
    }

    fun updateTimeOfActionType(actionType: ActionType, time: Long): Completable {
        return Completable.fromAction { dataBase[actionType] = time }
    }
}