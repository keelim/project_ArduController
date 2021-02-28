package com.keelim.arducon.ui.controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ControllerViewModel: ViewModel() {
    private var _text:MutableLiveData<String> = MutableLiveData()
    val text: LiveData<String> = _text



}