package com.rustedwheel.android.banktransactions.services

import com.rustedwheel.android.banktransactions.models.Transaction
import com.rustedwheel.android.banktransactions.models.dao.TransactionDAO
import com.rustedwheel.android.banktransactions.network.BTApi
import com.rustedwheel.android.banktransactions.servcies.NetworkService
import com.rustedwheel.android.banktransactions.servcies.NetworkServiceImpl
import com.rustedwheel.android.banktransactions.servcies.TransactionsService
import com.rustedwheel.android.banktransactions.viewmodels.home.HomeViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.util.*

class NetworkServiceTest {

    private lateinit var mockApi: BTApi

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockApi = Mockito.mock(BTApi::class.java)
    }

    @Test
    fun testCallSuccess() = runBlocking {
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
        val transactions = listOf(transaction1, transaction2)
        val networkService = NetworkServiceImpl(mockApi)
        Mockito.`when`(mockApi.getTransactions()).thenReturn(CompletableDeferred(Response.success(transactions)))

        val response = networkService.call {
            it.getTransactions()
        }

        assertEquals(transactions, response.body())
    }

}