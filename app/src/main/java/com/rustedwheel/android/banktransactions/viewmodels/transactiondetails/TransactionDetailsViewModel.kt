package com.rustedwheel.android.banktransactions.viewmodels.transactiondetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rustedwheel.android.banktransactions.models.Transaction
import com.rustedwheel.android.banktransactions.models.dao.TransactionDAO

class TransactionDetailsViewModel(
    private val transactionId: Int,
    private val transactionsDAO: TransactionDAO
) : ViewModel() {

    val transaction = MutableLiveData<Transaction>()

    fun fetchTransactionDetails() {
        transaction.value = transactionsDAO.getTransaction(transactionId)
    }

}