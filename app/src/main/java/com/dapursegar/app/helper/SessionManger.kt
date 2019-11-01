package com.dapursegar.app.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SessionManger {

    private fun getPref(context: Context?): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun putString(context: Context?, key: String, value: String?) {
        getPref(context).edit().putString(key, value).apply()
    }

    fun getString(context: Context?, key: String): String? {
        return getPref(context).getString(key, "")
    }

    fun putInt(context: Context, key: String, value: Int) {
        getPref(context).edit().putInt(key, value).apply()
    }

    fun getInt(context: Context, key: String): Int {
        return getPref(context).getInt(key, 0)
    }

    fun putLong(context: Context, key: String, value: Long) {
        getPref(context).edit().putLong(key, value).apply()
    }

    fun getLong(context: Context, key: String): Long {
        return getPref(context).getLong(key, 0)
    }

    fun putBoolean(context: Context, key: String, value: Boolean?) {
        getPref(context).edit().putBoolean(key, value!!).apply()
    }

    fun getBoolean(context: Context, key: String): Boolean {
        return getPref(context).getBoolean(key, false)
    }

    fun clearAll(context: Context) {
        getPref(context).edit().clear().apply()
    }

    fun clear(context: Context, key: String) {
        getPref(context).edit().remove(key).apply()
    }

    /**
     * This is for new object key server API_SERVICE!
     * */

    fun getApiService(context: Context): String {
        return getPref(context).getString(Constants.CUSTOM_API, "http://192.168.1.1")!!
    }

    /**
     *  This is for authentication request
     * */

    fun getUUID(context: Context?): String {
        return getPref(context).getString(Constants.UUID, "")!!
    }
}
