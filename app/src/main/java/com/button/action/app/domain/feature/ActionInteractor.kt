package com.button.action.app.domain.feature

import android.content.Context
import android.net.ConnectivityManager
import com.button.action.app.data.feature.action.ActionRepository
import com.button.action.app.data.feature.history.HistoryRepository
import com.button.action.app.domain.exeption.NoActionException
import com.button.action.app.domain.type.ActionType
import com.button.action.app.domain.type.DayOfWeek
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class ActionInteractor
@Inject
constructor(
    private val actionRepository: ActionRepository,
    private val historyRepository: HistoryRepository,
    context: Context
) {
    private var cm = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

    fun getNextAction(): Single<ActionType> {
        val isConnected = isConnected()
        val day = getCurrentDay()
        val currentTime = java.util.Calendar.getInstance().timeInMillis

        return historyRepository.getSavedTimeInfo()
            .take(1)
            .singleOrError()
            .flatMap { timeMap ->
                actionRepository.getActions().map { list ->
                    return@map list
                        .asSequence()
                        .filter { it.enable }
                        .filter { it.days.contains(day) }
                        .filter {
                            if (it.actionType == ActionType.TOAST) {
                                isConnected
                            } else {
                                false
                            }
                        }
                        .filter {
                            val savedTime = timeMap[it.actionType]
                            if(savedTime == null) true
                            else {
                                (currentTime - savedTime ) >= it.couldDown
                            }
                        }
                        .sortedBy { it.priority }
                        .toList()
                }.map {
                    // TODO random if the same priority
                    val action = it.firstOrNull() ?: throw NoActionException()
                    action.actionType
                }.flatMap {
                    historyRepository.updateTimeOfActionType(it, currentTime)
                        .andThen(Single.just(it))
                }
            }
    }

    private fun isConnected(): Boolean {
        val activeNetwork = cm.activeNetworkInfo ?: return false
        return activeNetwork.isConnected
    }

    private fun getCurrentDay(): DayOfWeek {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.DAY_OF_WEEK).run {
            when (this) {
                Calendar.MONDAY -> DayOfWeek.MONDAY
                Calendar.TUESDAY -> DayOfWeek.TUESDAY
                Calendar.WEDNESDAY -> DayOfWeek.WEDNESDAY
                Calendar.THURSDAY -> DayOfWeek.THURSDAY
                Calendar.FRIDAY -> DayOfWeek.FRIDAY
                Calendar.SATURDAY -> DayOfWeek.SATURDAY
                Calendar.SUNDAY -> DayOfWeek.SUNDAY
                else -> error("unsupported type")
            }
        }
    }

}