package com.rustedwheel.android.banktransactions.servcies

import com.rustedwheel.android.banktransactions.models.Transaction

interface TransactionsService {
    suspend fun fetchTransactions(): List<Transaction>
}

class TransactionsServiceImpl(private val networkService: NetworkService): TransactionsService {

    override suspend fun fetchTransactions(): List<Transaction> {
        val response = networkService.call {
            it.getTransactions()
        }
        return response.body() ?: listOf()
    }

}