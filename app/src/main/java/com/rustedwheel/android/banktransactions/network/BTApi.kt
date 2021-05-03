package com.rustedwheel.android.banktransactions.network

import com.rustedwheel.android.banktransactions.models.Transaction
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface BTApi {

    @GET("api/v1/transactions")
    fun getTransactions(): Deferred<List<Transaction>>

}