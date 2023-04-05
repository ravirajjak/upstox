package com.appturbo.base

import android.app.Application

class BSApplication :Application(){
    init {
        instance = this
    }

    //singleton reference
    companion object {
        private var instance: BSApplication? = null
        fun getContext(): BSApplication {
            return instance as BSApplication
        }
    }
}
