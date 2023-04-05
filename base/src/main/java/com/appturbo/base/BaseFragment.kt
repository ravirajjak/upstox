package com.appturbo.base


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception


abstract class BaseFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setObserveData()

    }

    inline fun <reified T : ViewModel> getViewModel(fragment: ViewModelStoreOwner): T {
        return ViewModelProvider(fragment).get(T::class.java)
    }

    fun openIntentWithFinish(mClass: Class<*>, isDelay: Boolean = false) {

        try {
            lifecycleScope.launch {
                if (isDelay)
                    delay(3000L)
                val intent = Intent(requireActivity(), mClass)
                startActivity(intent)
                requireActivity().finish()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    abstract fun setViewModel()
    abstract fun setObserveData()

}