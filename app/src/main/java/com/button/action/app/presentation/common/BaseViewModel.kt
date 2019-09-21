package com.button.action.app.presentation.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.button.action.app.presentation.common.livedata.SingleLiveEvent
import com.button.action.app.presentation.utils.RxDisposable

open class BaseViewModel : ViewModel() {
    var errorMessage = SingleLiveEvent<String>()
    var uploading = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        RxDisposable.unsubscribe(this)
    }
}