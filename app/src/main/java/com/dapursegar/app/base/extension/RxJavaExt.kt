package com.dapursegar.app.base.extension

import com.dapursegar.app.base.dialog.BaseLoading
import com.dapursegar.app.base.network.BaseResult
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ramana on 09-Nov-19.
 */

internal fun <Response> Flowable<Response>.getData(
    loading: BaseLoading,
    success: Response.() -> Unit,
    exception: Throwable.() -> Unit
): Disposable {
    loading.show()
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                loading.dismiss()
                it.apply { success(this) }
            },
            {
                loading.dismiss()
                it.printStackTrace()
                it.apply { exception(this) }
            }
        )
}

internal fun <Data> Flowable<Data>.getBaseResult(
    success: Data.() -> Unit,
    exception: Throwable.() -> Unit
): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { it.apply { success(this) } },
            {
                it.printStackTrace()
                it.apply { exception(this) }
            }
        )
}

internal fun <Data> Flowable<Data>.getBaseResult(
    loading: BaseLoading,
    success: Data.() -> Unit,
    exception: Throwable.() -> Unit
): Disposable {
    loading.show()
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                loading.dismiss()
                it.apply { success(this) }
            },
            {
                loading.dismiss()
                it.printStackTrace()
                it.apply { exception(this) }
            }
        )
}

internal fun <Object> createFlowBackPress(block: FlowableEmitter<Object>.() -> Unit): Flowable<Object> {
    return Flowable.create({ block(it) }, BackpressureStrategy.BUFFER)
}