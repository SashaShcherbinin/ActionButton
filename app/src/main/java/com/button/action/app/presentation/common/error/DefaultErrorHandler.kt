package com.button.action.app.presentation.common.error

import com.button.action.app.domain.exeption.NoActionException
import timber.log.Timber
import javax.inject.Inject

class DefaultErrorHandler
@Inject
constructor() : ErrorHandler {

    override fun handleError(throwable: Throwable) {
        handleError(throwable, null)
    }

    override fun handleError(throwable: Throwable, errorView: ((message: String) -> Unit)?) {
        Timber.e(throwable)

        if (errorView != null) {
            var message: String? = null
            message = if (throwable is NoActionException) {
                "No more action, try nex time"
            } else {
                "Connection error!!!"
            }
            errorView.invoke(message)
        }
    }

}
