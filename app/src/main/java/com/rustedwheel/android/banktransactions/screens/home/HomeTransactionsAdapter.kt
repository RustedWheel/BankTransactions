package com.rustedwheel.android.banktransactions.screens.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rustedwheel.android.banktransactions.models.Transaction

class HomeTransactionsAdapter(private val callback: (Transaction) -> Unit) :
    RecyclerView.Adapter<HomeTransactionViewHolder>() {

    private val transactionsList: MutableList<Transaction> = arrayListOf()

    init {
        setHasStableIds(true)
    }

    override fun getItemCount(): Int = transactionsList.size

    override fun getItemId(position: Int): Long = transactionsList[position].id.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTransactionViewHolder =
        HomeTransactionViewHolder.newInstance(parent, callback)

    override fun onBindViewHolder(holder: HomeTransactionViewHolder, position: Int) {
        holder.bind(transactionsList[position])
    }

    fun setTransactions(transactions: List<Transaction>) {
        transactionsList.clear()
        transactionsList.addAll(transactions)
        notifyDataSetChanged()
    }
}