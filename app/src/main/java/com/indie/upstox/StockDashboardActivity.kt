package com.indie.upstox

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.appturbo.base.BaseActivity
import com.indie.upstox.adapte.PortfolioAdapter
import com.indie.upstox.databinding.ActivityMainBinding
import com.indie.upstox.event.Event

class StockDashboardActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel by viewModels<StockDashboardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init() {
        callPortFolioApi()
        setViewModel()
        observeState()
        setOnSwipeListener()
    }

    private fun setOnSwipeListener() {
        binding.swiprefresh.setOnRefreshListener {
            callPortFolioApi()
            binding.swiprefresh.isRefreshing = false
        }
    }

    private fun callPortFolioApi() {
        viewModel.getPortfolio(this)
    }


    override fun setViewModel() {
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }

    override fun observeState() {
        viewModel.currentState.observe(this) {
            it as Event
            when (it) {
                Event.IS_LOADED -> {
                    setAdapter()
                }
                Event.NO_MATCH -> {
                }
            }
        }
    }


    private fun setAdapter() {
        binding.recylerview.apply {
            layoutManager = LinearLayoutManager(this@StockDashboardActivity)
            adapter = PortfolioAdapter(viewModel.vmList)
        }
    }
}