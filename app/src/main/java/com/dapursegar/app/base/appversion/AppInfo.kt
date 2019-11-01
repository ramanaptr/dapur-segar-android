package com.fastaloka.app.base.appversion

import android.app.Activity
import android.content.pm.PackageManager

object AppInfo {

    fun version(act: Activity): String {
        val manager = act.packageManager
        val info = manager.getPackageInfo(act.packageName, PackageManager.GET_ACTIVITIES)
        return "Version " + info.versionName
    }
}