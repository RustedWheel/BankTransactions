package com.rustedwheel.android.banktransactions.viewmodels.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustedwheel.android.banktransactions.models.Transaction
import com.rustedwheel.android.banktransactions.network.BTApiError
import com.rustedwheel.android.banktransactions.servcies.TransactionsService
import com.rustedwheel.android.banktransactions.utils.Event
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val transactionsService: TransactionsService) : ViewModel() {

    val transactions = MutableLiveData<List<Transaction>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<Event<String>>()

    fun fetchTransactions() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                transactions.value = transactionsService.fetchTransactions()
            } catch (error: Exception) {
                when (error) {
                    is BTApiError -> {
                        errorMessage.value = Event(error.message)
                    }
                    else -> {
                        errorMessage.value = Event("Something went wrong")
                    }
                }
            } finally {
                isLoading.value = false
            }
        }
    }

    fun onTransactionSelected(transaction: Transaction) {

    }

}