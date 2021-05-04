package com.rustedwheel.android.banktransactions.models.dao

import com.rustedwheel.android.banktransactions.models.Transaction

class MockTransactionDAO: TransactionDAO {

    private val transactions = mutableListOf<Transaction>()

    override fun getTransaction(id: Int): Transaction? = transactions.firstOrNull { it.id == id }

    override fun getTransactions(): List<Transaction> = transactions.toList()

    override fun createFromId(id: Int): Transaction {
        val transaction = Transaction().apply { this.id = id }
        transactions.add(transaction)
        return transaction
    }

    override fun createFromUntracked(untrackedTransaction: Transaction): Transaction {
        transactions.add(untrackedTransaction)
        return untrackedTransaction
    }

    override fun edit(block: (TransactionDAO) -> Unit) {
        block(this)
    }
}