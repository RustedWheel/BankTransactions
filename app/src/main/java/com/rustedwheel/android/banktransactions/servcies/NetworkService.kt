package com.rustedwheel.android.banktransactions.servcies

import com.rustedwheel.android.banktransactions.network.BTApi
import com.rustedwheel.android.banktransactions.network.BTApiError
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface NetworkService {
    suspend fun <T> call(method: (BTApi) -> Deferred<Response<T>>): Response<T>
}

class NetworkServiceImpl(private val api: BTApi): NetworkService {

    override suspend fun <T> call(method: (BTApi) -> Deferred<Response<T>>): Response<T> {
        return validateResponse(method.invoke(api).await())
    }

    private fun <T> validateResponse(response: Response<T>): Response<T> {
        if (!response.isSuccessful) {
            throw BTApiError(response.raw())
        }
        return response
    }
}