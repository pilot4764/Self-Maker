package com.artsam.selfmaker.ui.veda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VedaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Veda Fragment"
    }
    val text: LiveData<String> = _text
}