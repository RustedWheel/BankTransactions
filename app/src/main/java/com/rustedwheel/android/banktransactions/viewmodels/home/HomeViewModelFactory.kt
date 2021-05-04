package com.rustedwheel.android.banktransactions.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rustedwheel.android.banktransactions.servcies.TransactionsService

class HomeViewModelFactory(private val transactionsService: TransactionsService): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(transactionsService) as T
    }

}