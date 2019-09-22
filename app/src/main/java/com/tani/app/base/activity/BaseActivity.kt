package com.tani.app.base.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.tani.app.R
import com.tani.app.helper.DialogLoading
import com.tani.app.helper.Utils
import com.tani.app.helper.ViewUtils
import kotlinx.android.synthetic.main.default_toolbar.*
import org.jetbrains.anko.toast

abstract class BaseActivity : AppCompatActivity() {

    private var baseLoading: DialogLoading? = null

    private fun runPermission() {
//        runWithPermissions(Manifest.permission.READ_CONTACTS) {}
//        runWithPermissions(Manifest.permission.CAMERA) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        onCreate(savedInstanceState, initToolbar())
        onClick()
        initDialog()
        screenStatus()
        networkCheck()
    }

    private fun initToolbar(): ActionBar? {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            return ViewUtils.initToolbar(tvTitle, setTitle(), toolbar, supportActionBar)
        }
        return supportActionBar
    }

    override fun onResume() {
        super.onResume()
        runPermission()
//        RemoteConfig.runWithExecutor(this)
    }

    private fun screenStatus() {
        Utils.adjustLayoutResize(this)
        Utils.changeColorStatusBar(this, statusBarColor())
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun initDialog() {
        baseLoading = DialogLoading(this)
    }

    private fun networkCheck() {
        if (!Utils.isNetworkAvailable(this)) toast(getString(R.string.default_connection_error))
    }

    protected abstract fun statusBarColor(): Int

    protected abstract fun setTitle(): String

    protected abstract fun setLayout(): Int

    protected abstract fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?)

    protected abstract fun onClick()
}
