package com.indie.upstox

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.appturbo.base.BaseViewModel
import com.appturbo.base.util.getCurrentValue
import com.appturbo.base.util.getInvestmentValue
import com.appturbo.base.util.mainThread
import com.appturbo.base.util.toast
import com.indie.upstox.event.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockDashboardViewModel : BaseViewModel() {

    val vmList = mutableListOf<RowPortfolioStocksViewModel>()
    val totalCurrentValue = MutableLiveData("")
    val totalInvestment = MutableLiveData("")
    val todaysPL = MutableLiveData("")
    val totalPL = MutableLiveData("")

    fun getPortfolio(activity: StockDashboardActivity) {
        activity.lifecycleScope.launch(Dispatchers.IO) {

            val response = mApiService.getPortfolio()

            if (response.isSuccessful) {

                try {

                    val data = response.body()
                    vmList.clear()
                    var tempSumCurrentValue = 0.0
                    var tempTotalInvestment = 0.0
                    var tempSumPNL = 0.0
                    var totalPNL = 0.0
                    data?.mData?.forEach {

                        it.currentValue = getCurrentValue(it.ltp, it.quantity.toDouble())
                        it.investmentValue = getInvestmentValue(it.avgPrice.toDouble(), it.quantity)
                        tempSumCurrentValue += it.currentValue
                        tempTotalInvestment += it.investmentValue
                        it.sumCurrentValue = tempSumCurrentValue
                        it.sumInvestmentValue = tempTotalInvestment
                        it.individualPNL = it.currentValue - it.investmentValue
                        totalPNL = it.sumCurrentValue - it.sumInvestmentValue
                        tempSumPNL += ((it.close - it.ltp) * it.quantity)
                        it.todaysPNL = tempSumPNL
                    }

                    data?.mData?.let {
                        it.forEach { pm ->
                            vmList.add(RowPortfolioStocksViewModel(pm))
                        }
                    }

                    activity.mainThread {
                        totalCurrentValue.value = tempSumCurrentValue.toString()
                        totalInvestment.value = tempTotalInvestment.toString()
                        todaysPL.value = tempSumPNL.toString()
                        totalPL.value = totalPNL.toString()
                        currentState.value = Event.IS_LOADED
                    }

                } catch (e: Exception) {
                    println("Exception " + e.message)
                    context.getString(R.string.something_went_wrong) toast activity
                }
            } else {
                context.getString(R.string.in_appropriate_data) toast activity
            }
        }
    }
}