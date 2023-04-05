package com.appturbo.base.util

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

infix fun String.toast(activity: Activity) {
    activity.runOnUiThread {
        Toast.makeText(activity, this, Toast.LENGTH_SHORT).show()
    }
}

fun getCurrentValue(ltp: Double, qty: Double): Double {
    return ltp * qty
}

//Mistake in UI or either in documentation total investment in documentation is subtracting
// while in UI if stock is multiplied the
// numbers are exact same matching with the UI
fun getInvestmentValue(avg_price: Double, qty: Int): Double {
    return avg_price * qty
}

inline fun AppCompatActivity.mainThread(crossinline lambda: () -> Unit) {
    lifecycleScope.launch(Dispatchers.Main) {
        lambda()
    }
}