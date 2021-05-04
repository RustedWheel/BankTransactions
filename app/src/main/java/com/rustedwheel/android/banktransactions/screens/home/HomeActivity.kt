package com.rustedwheel.android.banktransactions.screens.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.rustedwheel.android.banktransactions.BTService
import com.rustedwheel.android.banktransactions.R
import com.rustedwheel.android.banktransactions.models.Transaction
import com.rustedwheel.android.banktransactions.screens.core.BTActivity
import com.rustedwheel.android.banktransactions.viewmodels.home.HomeViewModel
import com.rustedwheel.android.banktransactions.viewmodels.home.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BTActivity() {

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(BTService.transactionService, BTService.transactionDAO)
    }
    private val homeTransactionsAdapter by lazy { HomeTransactionsAdapter(viewModel::onTransactionSelected) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeTransactionsList.adapter = homeTransactionsAdapter
        homeRefresh.setOnRefreshListener {
            viewModel.fetchTransactions()
        }

        viewModel.transactions.observe(this, Observer {
            syncData(it)
        })
        viewModel.isLoading.observe(this, Observer {
            syncIsLoading(it)
        })
        viewModel.errorMessage.observe(this, Observer { event ->
            event.handleIfNotHandled {
                showErrorMessage(it)
            }
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchTransactions()
    }

    private fun syncData(transactions: List<Transaction>) {
        homeTransactionsAdapter.setTransactions(transactions)
    }

    private fun syncIsLoading(isLoading: Boolean) {
        homeRefresh.isRefreshing = isLoading
    }
}