package com.rustedwheel.android.banktransactions.viewmodels.transactiondetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rustedwheel.android.banktransactions.models.dao.TransactionDAO
import com.rustedwheel.android.banktransactions.viewmodels.home.HomeViewModel

class TransactionDetailsViewModelFactory(
    private val transactionId: Int,
    private val transactionDAO: TransactionDAO
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionDetailsViewModel(transactionId, transactionDAO) as T
    }

}