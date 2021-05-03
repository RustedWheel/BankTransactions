package com.rustedwheel.android.banktransactions

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rustedwheel.android.banktransactions.network.BTApi
import com.rustedwheel.android.banktransactions.servcies.NetworkService
import com.rustedwheel.android.banktransactions.servcies.NetworkServiceImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BTService {

    val btApi: BTApi by lazy {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return@lazy retrofit.create(BTApi::class.java)
    }

    val networkService: NetworkService by lazy {
        NetworkServiceImpl(btApi)
    }
}