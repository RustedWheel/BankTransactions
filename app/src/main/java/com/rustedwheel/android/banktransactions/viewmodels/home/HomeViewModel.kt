package com.rustedwheel.android.banktransactions.viewmodels.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustedwheel.android.banktransactions.models.Transaction
import com.rustedwheel.android.banktransactions.models.dao.TransactionDAO
import com.rustedwheel.android.banktransactions.network.BTApiError
import com.rustedwheel.android.banktransactions.servcies.TransactionsService
import com.rustedwheel.android.banktransactions.utils.Event
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(
    private val transactionsService: TransactionsService,
    private val transactionsDAO: TransactionDAO
) : ViewModel() {

    val transactions = MutableLiveData<List<Transaction>>()
    val isLoading = MutableLiveData<Boolean>()
    val selectedTransaction = MutableLiveData<Event<Transaction>>()
    val errorMessage = MutableLiveData<Event<String>>()

    fun fetchTransactions() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                transactionsService.fetchTransactions()
                transactions.value = transactionsDAO.getTransactions()
            } catch (error: Exception) {
                when (error) {
                    is BTApiError -> {
                        errorMessage.value = Event(error.message)
                    }
                    else -> {
                        errorMessage.value = Event(error.message ?: "Something went wrong")
                    }
                }
            } finally {
                isLoading.value = false
            }
        }
    }

    fun onTransactionSelected(transaction: Transaction) {
        selectedTransaction.value = Event(transaction)
    }
}