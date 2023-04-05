package com.appturbo.base.util

import android.util.Log

interface Logger {
    fun logD(message: String)
}

const val TAG = ""

object FLogger : Logger {
    override fun logD(message: String) {
        Log.d(TAG, message)
    }
}