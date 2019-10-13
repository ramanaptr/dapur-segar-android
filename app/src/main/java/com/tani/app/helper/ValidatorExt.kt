package com.tani.app.helper

import android.text.TextUtils
import android.util.Patterns

/**
 * Created by Ramana on 13-Oct-19.
 */

fun String.validateEmail(): Boolean {
    if (this.isEmpty()) return false
    val matcher = Patterns.EMAIL_ADDRESS.matcher(this)
    return matcher.matches()
}

fun String.validatePhone(): Boolean {
    if (TextUtils.isEmpty(this) || this.length <= 6) return false
    val matcher = Patterns.PHONE.matcher(this)
    return matcher.matches()
}

fun String.validatePassword(minimal: Long): Boolean {
    return this.length >= minimal

}

fun String.validatePassword(rePassword: String): Boolean {
    return if (this.isEmpty()) false else rePassword == this
}