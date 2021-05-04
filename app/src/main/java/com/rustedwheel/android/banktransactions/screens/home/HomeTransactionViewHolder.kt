package com.rustedwheel.android.banktransactions.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rustedwheel.android.banktransactions.R
import com.rustedwheel.android.banktransactions.models.Transaction
import com.rustedwheel.android.banktransactions.utils.formatDate
import com.rustedwheel.android.banktransactions.utils.formatDouble
import kotlinx.android.synthetic.main.list_item_transaction.view.*

class HomeTransactionViewHolder(itemView: View, callback: (Transaction) -> Unit) : RecyclerView.ViewHolder(itemView) {

    private var transaction: Transaction? = null

    init {
        itemView.setOnClickListener {
            transaction?.let(callback::invoke)
        }
    }

    fun bind(transaction: Transaction) {
        this.transaction = transaction

        with(itemView) {
            itemTransactionSummary.text = transaction.summaryDescription
            itemTransactionDate.text = transaction.transactionDate.formatDate()

            if (transaction.isCredit) {
                itemTransactionAmount.text = context.getString(
                    R.string.transaction_amount_credit,
                    transaction.creditAmount.formatDouble(2, true)
                )
                itemTransactionAmount.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
            } else {
                itemTransactionAmount.text = context.getString(
                    R.string.transaction_amount_debit,
                    transaction.debitAmount.formatDouble(2, true)
                )
                itemTransactionAmount.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
            }
        }
    }

    companion object {
        fun newInstance(parent: ViewGroup, callback: (Transaction) -> Unit) = HomeTransactionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_transaction, parent, false
            ),
            callback
        )
    }
}