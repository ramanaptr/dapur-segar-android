package com.dapursegar.app.base.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dapursegar.app.R

/**
 * Created by Ramana on 22-Sep-19.
 */

fun ImageView.load(imageUri: Any) {
    Glide.with(context)
        .load(imageUri)
        .apply(
            RequestOptions()
                .error(R.drawable.ic_profile_off)
        )
        .into(this)
}