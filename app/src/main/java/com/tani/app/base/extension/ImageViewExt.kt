package com.tani.app.base.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.tani.app.R
import org.jetbrains.anko.toast

/**
 * Created by Ramana on 22-Sep-19.
 */

fun ImageView.load(imageAt: String) {
    try {
        Picasso.get().load(imageAt).into(this)
    } catch (e: Exception) {
        e.printStackTrace()
        context.toast(context.getString(R.string.default_error_message))
    }
}

fun ImageView.load(imageAt: Int) {
    try {
        Picasso.get().load(imageAt).into(this)
    } catch (e: Exception) {
        e.printStackTrace()
        context.toast(context.getString(R.string.default_error_message))
    }
}