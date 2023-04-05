package com.appturbo.base

import android.app.usage.UsageEvents.Event
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appturbo.base.repository.`interface`.ApiService

open class BaseViewModel : ViewModel() {

    var currentState = MutableLiveData<Any>()
    val context by lazy { BSApplication.getContext() }

    val mApiService by lazy {
        ApiService.create()
    }

    fun showToast(message: String) {
        Toast.makeText(BSApplication.getContext(), message, Toast.LENGTH_SHORT).show()
    }


}