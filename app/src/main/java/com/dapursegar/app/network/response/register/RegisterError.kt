package com.dapursegar.app.network.response.register


import com.google.gson.annotations.SerializedName

data class RegisterError(
    @SerializedName("customer_id")
    val customerId: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("user")
    val user: String
)