package com.artsam.selfmaker.utils

import android.util.Log
import com.artsam.selfmaker.BuildConfig

/*
* Logging in Kotlin — the right way
* https://muthuraj57.medium.com/logging-in-kotlin-the-right-way-d7a357bb0343
* */
inline fun Any.log(message: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.d(this::class.java.simpleName, message())
    }
}
