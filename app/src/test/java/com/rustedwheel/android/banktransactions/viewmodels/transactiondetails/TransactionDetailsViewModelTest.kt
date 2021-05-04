package com.rustedwheel.android.banktransactions.viewmodels.transactiondetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rustedwheel.android.banktransactions.MainCoroutineRule
import com.rustedwheel.android.banktransactions.models.dao.MockTransactionDAO
import com.rustedwheel.android.banktransactions.models.Transaction
import com.rustedwheel.android.banktransactions.models.dao.TransactionDAO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class TransactionDetailsViewModelTest {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var mockTransactionDAO: TransactionDAO

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockTransactionDAO = MockTransactionDAO()
    }

    @Test
    fun testFetchTransactionDetails() {
        val transactionId = 18
        val summary = "Test transaction"
        val transaction = Transaction().apply {
            id = transactionId
            summaryDescription = summary
        }
        mockTransactionDAO.createFromUntracked(transaction)

        val viewModel = TransactionDetailsViewModelFactory(transactionId, mockTransactionDAO).create(TransactionDetailsViewModel::class.java)
        viewModel.fetchTransactionDetails()

        assertEquals(summary, viewModel.transaction.value!!.summaryDescription)
    }

}