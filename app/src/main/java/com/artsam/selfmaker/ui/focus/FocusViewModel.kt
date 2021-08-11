package com.artsam.selfmaker.ui.focus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FocusViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is focus Fragment"
    }
    val text: LiveData<String> = _text
}