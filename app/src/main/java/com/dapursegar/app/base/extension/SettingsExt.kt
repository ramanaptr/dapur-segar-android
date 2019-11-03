package com.dapursegar.app.base.extension

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Ramana on 11-Oct-19.
 */

private const val DS_SETTINGS = "DS_SETTINGS"
private const val ANDROID_ID = "ANDROID_ID"
private const val API = "API"

private fun Context.settings(): SharedPreferences? {
    return getSharedPreferences(DS_SETTINGS, Context.MODE_PRIVATE)
}

private fun Context.editSettings(): SharedPreferences.Editor? {
    return settings()?.edit()
}

fun Context.clearAllSettings() {
    editSettings()?.clear()?.apply()
}

fun Context.clearSetting(key: String) {
    editSettings()?.remove(key)?.apply()
}

fun Context.saveAndroidID(value: String) {
    editSettings()?.putString(ANDROID_ID, value)?.apply()
}

fun Context.getSavedAndroidID(): String {
    return settings()?.getString(ANDROID_ID, "") as String
}
