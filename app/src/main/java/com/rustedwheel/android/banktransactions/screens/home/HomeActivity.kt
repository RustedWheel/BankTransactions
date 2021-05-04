package com.rustedwheel.android.banktransactions.screens.home

import android.os.Bundle
import androidx.activity.viewModels
import com.rustedwheel.android.banktransactions.BTService
import com.rustedwheel.android.banktransactions.R
import com.rustedwheel.android.banktransactions.screens.core.BTActivity
import com.rustedwheel.android.banktransactions.viewmodels.home.HomeViewModel
import com.rustedwheel.android.banktransactions.viewmodels.home.HomeViewModelFactory

class HomeActivity : BTActivity() {

    private val viewModel: HomeViewModel by viewModels { HomeViewModelFactory(BTService.transactionService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchTransactions()
    }
}