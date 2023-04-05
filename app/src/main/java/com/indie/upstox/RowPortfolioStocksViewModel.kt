package com.indie.upstox

import androidx.databinding.ObservableField
import com.appturbo.base.repository.model.PortfolioData
import com.indie.upstox.adapter.ViewModel

class RowPortfolioStocksViewModel(portfolioData: PortfolioData) : ViewModel {

    val symbol = ObservableField<String>(portfolioData.symbol)
    val quantity = ObservableField<String>(portfolioData.quantity.toString())
    val ltp = ObservableField<String>(portfolioData.ltp.toString())
    val individualPNL = ObservableField<String>(portfolioData.individualPNL.toString())

    override fun close() {

    }

}