package com.rustedwheel.android.banktransactions.models

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class TransactionTest {

    @Test
    fun testTransactionCalculateGst() {
        val amount = 115.0
        val actualGstPaidAmount = 15.0

        val transaction = Transaction().apply {
            id = 1
            summaryDescription = "test"
            transactionDate = Date()
            debitAmount = amount
        }

        assertEquals(actualGstPaidAmount, transaction.calculateGst(), 0.0)
    }

}