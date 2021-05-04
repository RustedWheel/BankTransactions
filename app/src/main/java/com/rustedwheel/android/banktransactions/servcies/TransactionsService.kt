package com.rustedwheel.android.banktransactions.servcies

import com.rustedwheel.android.banktransactions.models.dao.TransactionDAO

interface TransactionsService {
    suspend fun fetchTransactions()
}

class TransactionsServiceImpl(
    private val networkService: NetworkService,
    private val transactionDAO: TransactionDAO
) : TransactionsService {

    override suspend fun fetchTransactions() {
        val response = networkService.call {
            it.getTransactions()
        }

        transactionDAO.edit { dao ->
            response.body()?.forEach { untrackedTransaction ->
                dao.createFromUntracked(untrackedTransaction)
            }
        }
    }

}