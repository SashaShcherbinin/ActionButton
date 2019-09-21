package com.button.action.app.presentation.main

import com.button.action.app.domain.feature.ActionInteractor
import com.button.action.app.domain.type.ActionType
import com.button.action.app.presentation.common.BaseViewModel
import com.button.action.app.presentation.common.error.DefaultErrorHandler
import com.button.action.app.presentation.common.livedata.SingleLiveEvent
import com.button.action.app.presentation.utils.RxDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val actionInteractor: ActionInteractor,
    private val errorHandler: DefaultErrorHandler
) : BaseViewModel() {

    val animationEvent = SingleLiveEvent<Unit>()
    val toastEvent = SingleLiveEvent<String>()
    val callEvent = SingleLiveEvent<Unit>()
    val notificationEvent = SingleLiveEvent<Unit>()

    fun onActionClicked() {
        RxDisposable.manage(this, actionInteractor.getNextAction()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { uploading.value = true }
            .doAfterTerminate { uploading.value = false }
            .subscribe({ mapAction(it) },
                { error ->
                    errorHandler.handleError(error) { errorMessage.value = it }
                })
        )
    }

    private fun mapAction(action: ActionType) {
        when (action) {
            ActionType.ANIMATION -> {
                animationEvent.call()
            }
            ActionType.TOAST -> {
                toastEvent.value = "Action is Toast!"
            }
            ActionType.CALL -> {
                callEvent.call()
            }
            ActionType.NOTIFICATION -> {
                notificationEvent.call()
            }
        }
    }
}