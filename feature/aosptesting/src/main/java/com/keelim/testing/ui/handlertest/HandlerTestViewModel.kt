package com.keelim.testing.ui.handlertest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HandlerTestViewModel : ViewModel() {
    private var _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> = _counter

    init {
        _counter.value = 0
    }

    fun increase() {
        _counter.value = _counter.value!! + 500
    }

    fun decrease() {
        _counter.value = _counter.value!! - 500
        if (_counter.value!! < 0) _counter.value = 0
    }

}