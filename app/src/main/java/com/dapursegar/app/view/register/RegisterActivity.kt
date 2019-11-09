package com.dapursegar.app.view.register

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.extension.subscribeMainThread
import kotlinx.android.synthetic.main.register_activity.*
import org.jetbrains.anko.toast

/**
 * Created by Ramana on 21-Sep-19.
 */

class RegisterActivity : BaseActivity<RegisterViewModel>() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Login"

    override fun setLayout(): Int = R.layout.register_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onClick() {
        tvSkip.setOnClickListener { finish() }
        tvLogin.setOnClickListener { finish() }
        btnRegister.setOnClickListener { registerUser() }
    }

    private fun registerUser() {
        val payloadRequest = HashMap<String, String>()
        payloadRequest["password"] = "luterrinding"
        payloadRequest["email"] = "luterrinding@gmail.com"
        payloadRequest["full_name"] = "Luter Rinding"
        payloadRequest["customer_id"] = "2"

        disposable.add(
            viewModel.registerUser(payloadRequest).subscribeMainThread(loading, {
                toast(error.user)
            }, {
                message?.apply { toast(this) }
            })
        )
    }

    private fun initComponent() {

    }
}
 
