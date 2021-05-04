package com.rustedwheel.android.banktransactions.screens.transactiondetails

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.rustedwheel.android.banktransactions.BTService
import com.rustedwheel.android.banktransactions.R
import com.rustedwheel.android.banktransactions.models.Transaction
import com.rustedwheel.android.banktransactions.screens.core.BTActivity
import com.rustedwheel.android.banktransactions.utils.formatDate
import com.rustedwheel.android.banktransactions.utils.formatDouble
import com.rustedwheel.android.banktransactions.viewmodels.transactiondetails.TransactionDetailsViewModel
import com.rustedwheel.android.banktransactions.viewmodels.transactiondetails.TransactionDetailsViewModelFactory
import kotlinx.android.synthetic.main.activity_transaction_details.*

class TransactionDetailsActivity : BTActivity() {

    private val viewModel: TransactionDetailsViewModel by viewModels {
        TransactionDetailsViewModelFactory(
            intent.extras?.getInt(TRANSACTION_ID_KEY) ?: 0,
            BTService.transactionDAO
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_details)
        viewModel.transaction.observe(this, Observer {
            syncTransaction(it)
        })
    }

    private fun syncTransaction(transaction: Transaction) {
        val isDebit = !transaction.isCredit
        val amount = if (isDebit) transaction.debitAmount else transaction.creditAmount
        transactionDetailsId.text = transaction.id.toString()
        transactionDetailsSummary.text = transaction.summaryDescription
        transactionDetailsDate.text = transaction.transactionDate.formatDate()
        transactionDetailsType.text =
            getString(if (isDebit) R.string.label_debit else R.string.label_credit)
        transactionDetailsAmount.text =
            getString(R.string.transaction_amount, amount.formatDouble(2, true))
        transactionDetailsGstContent.isVisible = isDebit
        if (isDebit) {
            val gstPaid = transaction.calculateGst()
            transactionDetailsGst.text = getString(R.string.transaction_amount, gstPaid.formatDouble(2, true))
        }
    }

    companion object {
        private const val TRANSACTION_ID_KEY = "transactionIdKey"

        fun makeArgs(transactionId: Int): Bundle {
            return Bundle().apply {
                putInt(TRANSACTION_ID_KEY, transactionId)
            }
        }
    }
}