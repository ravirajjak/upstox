package com.indie.upstox.adapte

import com.indie.upstox.R
import com.indie.upstox.RowPortfolioStocksViewModel
import com.indie.upstox.adapter.DataBindingRecyclerViewAdapter
import com.indie.upstox.adapter.ViewModel
import java.util.HashMap

class PortfolioAdapter(vmList: List<ViewModel>) : DataBindingRecyclerViewAdapter(vmList) {
    internal var viewModelMap: MutableMap<Class<*>, Int> = HashMap()

    init {
        viewModelMap[RowPortfolioStocksViewModel::class.java] = R.layout.row_stocks
    }

    override fun getViewModelLayoutMap(): Map<Class<*>, Int> {
        return viewModelMap
    }

}