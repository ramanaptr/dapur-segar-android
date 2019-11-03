package com.ramana.movierm.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Ramana on 28-Sep-19.
 */

interface APIService {

    @GET
    fun getMovie(
        @Url url: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ResponseBody>

    @GET
    fun getTVShow(
        @Url url: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ResponseBody>

    @GET
    fun getMoviesBySearch(
        @Url url: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Call<ResponseBody>

    @GET
    fun getTVShowBySearch(
        @Url url: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Call<ResponseBody>

    @GET
    fun getReleaseToday(
        @Url url: String,
        @Query("api_key") apiKey: String,
        @Query("primary_release_date.gte") gte: String,
        @Query("primary_release_date.lte") lte: String
    ): Call<ResponseBody>

}