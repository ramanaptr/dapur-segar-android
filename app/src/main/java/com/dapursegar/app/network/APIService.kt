package com.dapursegar.app.network

import com.dapursegar.app.base.network.BaseResult
import com.dapursegar.app.network.response.register.RegisterData
import com.dapursegar.app.network.response.register.RegisterError
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {

    @FormUrlEncoded
    @POST
    fun registerUser(
        @Url url: String,
        @FieldMap map: Map<String, String>
    ): Call<BaseResult<RegisterData, RegisterError>>

}