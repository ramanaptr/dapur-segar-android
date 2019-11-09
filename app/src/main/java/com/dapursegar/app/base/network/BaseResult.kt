package com.dapursegar.app.base.network


import com.google.gson.annotations.SerializedName

data class BaseResult<D, E>(
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("exeTime")
    val exeTime: String,
    @SerializedName("data")
    val data: D,
    @SerializedName("error")
    val error: E
)