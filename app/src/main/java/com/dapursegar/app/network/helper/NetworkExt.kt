package com.dapursegar.app.network.helper

import com.dapursegar.app.base.extension.coroutineIO
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ramana on 28-Sep-19.
 */

internal fun <T> Call<T>.getResponseWithCoroutine(
    success: suspend CoroutineScope.(Response<T>) -> Unit,
    error: suspend CoroutineScope.(Throwable) -> Unit
): Call<T> {
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            coroutineIO {
                error(t)
            }
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            coroutineIO {
                success(response)
            }
        }

    })
    return this
}

internal fun <T> Call<T>.getResponse(
    success: Response<T>.() -> Unit,
    error: Throwable.() -> Unit
): Call<T> {
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            error(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            success(response)
        }

    })
    return this
}