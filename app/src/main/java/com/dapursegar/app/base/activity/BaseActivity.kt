package com.dapursegar.app.base.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.dapursegar.app.R
import com.dapursegar.app.base.dialog.BaseLoading
import com.dapursegar.app.base.extension.changeColorStatusBar
import com.dapursegar.app.base.extension.isNetworkAvailable
import com.dapursegar.app.base.extension.settingToolbar
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.default_toolbar.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseActivity<T : ViewModel> : AppCompatActivity() {

    val viewModel: T by lazy { getViewModel(viewModelClass()) }
    val disposable: CompositeDisposable by lazy { CompositeDisposable() }
    val loading: BaseLoading by lazy { BaseLoading(this) }

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<T> {
        return ((javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<T>)
            .kotlin
    }

    private fun runPermission() {
//        runWithPermissions(Manifest.permission.READ_CONTACTS) {}
//        runWithPermissions(Manifest.permission.CAMERA) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        onCreate(savedInstanceState, settingToolbar(tvTitle, setTitle(), toolbar))
        onClick()
        screenStatus()
        networkCheck()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        disposable.dispose()
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

    private fun networkCheck() {
        if (!isNetworkAvailable()) toast(getString(R.string.default_connection_error))
    }

    protected abstract fun statusBarColor(): Int

    protected abstract fun setTitle(): String

    protected abstract fun setLayout(): Int

    protected abstract fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?)

    protected abstract fun onClick()
}
