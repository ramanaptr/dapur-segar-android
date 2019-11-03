package com.dapursegar.app.network

import com.dapursegar.app.BuildConfig
import com.dapursegar.app.network.helper.getData
import com.ramana.movierm.network.APIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val instance by lazy {
    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
    Retrofit.Builder()
        .baseUrl(domain())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val apiService: APIService by lazy { instance.create(APIService::class.java) }

private fun domain(): String {
    val isDevMode = true
    return if (isDevMode) getData(BuildConfig.API_STAGING)
    else getData(BuildConfig.API_PRODUCTION)
}

/**
 * Endpoint
 * */