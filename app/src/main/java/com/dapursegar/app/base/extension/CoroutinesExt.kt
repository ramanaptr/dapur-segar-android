package com.dapursegar.app.base.extension

import android.view.View
import kotlinx.coroutines.*

/**
 * Created by Ramana on 05-Nov-19.
 */

internal fun coroutineMain(block: suspend CoroutineScope.() -> Unit): Job {
    return CoroutineScope(Dispatchers.Main).launch {
        block(this)
    }
}

internal fun coroutineIO(block: suspend CoroutineScope.() -> Unit): Job {
    return CoroutineScope(Dispatchers.IO).launch {
        block(this)
    }
}

internal fun coroutineDefault(block: suspend CoroutineScope.() -> Unit): Job {
    return CoroutineScope(Dispatchers.Default).launch {
        block(this)
    }
}

internal fun View.onClickWithCoroutineIO(block: suspend CoroutineScope.() -> Unit): Job? {
    var job: Job? = Job()
    setOnClickListener {
        job = CoroutineScope(Dispatchers.IO).launch {
            async {
                block(this)
            }
        }
    }
    return job
}

internal fun View.onClickWithCoroutineMain(block: suspend CoroutineScope.() -> Unit): Job? {
    var job: Job? = Job()
    setOnClickListener {
        job = CoroutineScope(Dispatchers.Main).launch {
            block(this)
        }
    }
    return job
}