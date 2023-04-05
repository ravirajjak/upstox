package com.appturbo.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.appturbo.base.repository.`interface`.ApiService
import com.appturbo.base.util.FLogger

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson


abstract class BaseActivity : AppCompatActivity() {


    val mApiService by lazy {
        ApiService.create()
    }
    val gson by lazy {
        Gson()
    }

    companion object {
    }

    fun logger(message: String) {
        FLogger.logD(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun openIntentWithFinish(mClass: Class<*>, isDelay: Boolean = false) {
        try {
            lifecycleScope.launch {
                if (isDelay)
                    delay(3000L)
                val intent = Intent(this@BaseActivity, mClass)
                startActivity(intent)
                finish()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun log(value: String) {
        Log.d(javaClass.simpleName, value)
    }


    inline fun <reified T : ViewModel> getViewModel(activity: ViewModelStoreOwner): T {
        return ViewModelProvider(activity).get(T::class.java)
    }

    fun setStatusBarColor(resource: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(applicationContext, resource)
        }
    }

    fun showSnackBar(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    abstract fun setViewModel()
    abstract fun observeState()
}