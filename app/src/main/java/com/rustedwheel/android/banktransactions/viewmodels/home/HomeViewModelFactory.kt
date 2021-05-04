package com.rustedwheel.android.banktransactions.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rustedwheel.android.banktransactions.models.dao.TransactionDAO
import com.rustedwheel.android.banktransactions.servcies.TransactionsService

class HomeViewModelFactory(
    private val transactionsService: TransactionsService,
    private val transactionDAO: TransactionDAO
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(transactionsService, transactionDAO) as T
    }

}