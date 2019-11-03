package com.ramana.movierm.network.helper

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ramana on 28-Sep-19.
 */

fun Call<ResponseBody>.getResponse(
    success: (Response<ResponseBody>) -> Unit,
    error: (Throwable) -> Unit
): Call<ResponseBody> {
    enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
            t?.apply(error)
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?) {
            if (response != null) {
                response.let(success)
            } else {
                error(Throwable("Failed to get response"))
            }
        }

    })
    return this
}