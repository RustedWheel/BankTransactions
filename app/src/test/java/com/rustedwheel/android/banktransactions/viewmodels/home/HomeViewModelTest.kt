package com.rustedwheel.android.banktransactions.viewmodels.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rustedwheel.android.banktransactions.MainCoroutineRule
import com.rustedwheel.android.banktransactions.models.dao.MockTransactionDAO
import com.rustedwheel.android.banktransactions.models.Transaction
import com.rustedwheel.android.banktransactions.models.dao.TransactionDAO
import com.rustedwheel.android.banktransactions.network.BTApi
import com.rustedwheel.android.banktransactions.servcies.NetworkService
import com.rustedwheel.android.banktransactions.servcies.NetworkServiceImpl
import com.rustedwheel.android.banktransactions.servcies.TransactionsService
import com.rustedwheel.android.banktransactions.servcies.TransactionsServiceImpl
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.util.*

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var mockApi: BTApi
    private lateinit var networkService: NetworkService
    private lateinit var transactionsService: TransactionsService
    private lateinit var mockTransactionDAO: TransactionDAO
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockApi = Mockito.mock(BTApi::class.java)
        mockTransactionDAO = MockTransactionDAO()
        networkService = NetworkServiceImpl(mockApi)
        transactionsService = TransactionsServiceImpl(networkService, mockTransactionDAO)
        viewModel = HomeViewModelFactory(transactionsService, mockTransactionDAO).create(HomeViewModel::class.java)
    }

    @Test
    fun testFetchTransactions() = runBlocking {
        val transaction1 = Transaction().apply {
            id = 1
            summaryDescription = "test"
            transactionDate = Date()
            debitAmount = 115.00
        }
        val transaction2 = Transaction().apply {
            id = 2
            summaryDescription = "test 2"
            transactionDate = Date()
            debitAmount = 489.98
        }
        val transaction3 = Transaction().apply {
            id = 3
            summaryDescription = "test 3"
            transactionDate = Date()
            debitAmount = 88.15
        }
        val transactions = listOf(transaction1, transaction2, transaction3)

        Mockito.`when`(mockApi.getTransactions()).thenReturn(CompletableDeferred(Response.success(transactions)))

        viewModel.fetchTransactions()

        assertEquals(transactions, viewModel.transactions.value)
    }

    @Test
    fun testOnTransactionSelected() {
        val transaction = Transaction().apply {
            id = 1
            summaryDescription = "test"
            transactionDate = Date()
            debitAmount = 115.00
        }
        viewModel.onTransactionSelected(transaction)

        assertEquals(transaction, viewModel.selectedTransaction.value!!.peekContent())
    }

}