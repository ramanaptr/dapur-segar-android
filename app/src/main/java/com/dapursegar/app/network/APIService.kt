package com.dapursegar.app.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {

    @GET
    fun getMovie(
        @Url url: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ResponseBody>

}