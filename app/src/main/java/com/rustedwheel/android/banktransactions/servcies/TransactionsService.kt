package com.rustedwheel.android.banktransactions.servcies

import com.rustedwheel.android.banktransactions.models.dao.TransactionDAO
import io.realm.Realm

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

        response.body()?.forEach {
            Realm.getDefaultInstance().executeTransaction { realm ->
                transactionDAO.createFromUntracked(it)
            }
        }
    }

}