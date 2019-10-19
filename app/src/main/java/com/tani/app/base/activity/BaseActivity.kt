package com.tani.app.base.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.tani.app.R
import com.tani.app.base.dialog.BaseLoading
import com.tani.app.base.extension.changeColorStatusBar
import com.tani.app.base.extension.isNetworkAvailable
import com.tani.app.base.extension.settingToolbar
import kotlinx.android.synthetic.main.default_toolbar.*
import org.jetbrains.anko.toast

abstract class BaseActivity : AppCompatActivity() {

    private var baseLoading: BaseLoading? = null

    private fun runPermission() {
//        runWithPermissions(Manifest.permission.READ_CONTACTS) {}
//        runWithPermissions(Manifest.permission.CAMERA) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        onCreate(savedInstanceState, settingToolbar(tvTitle, setTitle(), toolbar))
        onClick()
        initDialog()
        screenStatus()
        networkCheck()
    }

    override fun onResume() {
        super.onResume()
        runPermission()
//        RemoteConfig.runWithExecutor(this)
    }

    private fun screenStatus() {
        changeColorStatusBar(statusBarColor())
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun initDialog() {
        baseLoading = BaseLoading(this)
    }

    private fun networkCheck() {
        if (!isNetworkAvailable()) toast(getString(R.string.default_connection_error))
    }

    protected abstract fun statusBarColor(): Int

    protected abstract fun setTitle(): String

    protected abstract fun setLayout(): Int

    protected abstract fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?)

    protected abstract fun onClick()
}
