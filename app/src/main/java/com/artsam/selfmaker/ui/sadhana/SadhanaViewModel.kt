package com.artsam.selfmaker.ui.sadhana

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SadhanaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sadhana Fragment"
    }
    val text: LiveData<String> = _text
}