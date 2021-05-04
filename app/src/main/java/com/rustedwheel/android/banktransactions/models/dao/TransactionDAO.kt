package com.rustedwheel.android.banktransactions.models.dao

import com.rustedwheel.android.banktransactions.models.Transaction
import io.realm.Realm
import io.realm.kotlin.where

interface TransactionDAO {
    fun getTransaction(id: String): Transaction?
    fun getTransactions(): List<Transaction>
    fun createFromId(id: String): Transaction
    fun createFromUntracked(untrackedTransaction: Transaction): Transaction
}

class RealmTransactionDAO(private val realm: Realm = Realm.getDefaultInstance()) : TransactionDAO {

    override fun getTransaction(id: String): Transaction? =
        realm.where<Transaction>().equalTo("id", id).findFirst()

    override fun getTransactions(): List<Transaction> = realm.where<Transaction>().findAll()

    override fun createFromId(id: String): Transaction =
        realm.createObject(Transaction::class.java, id)

    override fun createFromUntracked(untrackedTransaction: Transaction): Transaction =
        realm.copyToRealmOrUpdate(untrackedTransaction)
}