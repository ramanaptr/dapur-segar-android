package com.dapursegar.app.view.register

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.extension.getBaseResult
import com.dapursegar.app.view.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by Ramana on 21-Sep-19.
 */

class RegisterActivity : BaseActivity<RegisterViewModel>() {

    private var isBack = false

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Login"

    override fun setLayout(): Int = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
        initIntentExtra()
    }

    private fun initComponent() {

    }

    private fun initIntentExtra() {
        intent?.getBooleanExtra("BACK_LOGIN", false)?.let { isBack ->
            this.isBack = isBack
        }
    }

    private fun backToLogin() {
        if (isBack) {
            startActivity<LoginActivity>()
            finish()
        }
    }

    override fun onClick() {
        tvSkip.setOnClickListener {
            backToLogin()
            finish()
        }
        tvLogin.setOnClickListener {
            backToLogin()
            finish()
        }
        btnRegister.setOnClickListener { registerUser() }
    }

    private fun registerUser() {
        val payloadRequest = HashMap<String, String>()
        payloadRequest["password"] = "luterrinding"
        payloadRequest["email"] = "luterrinding@gmail.com"
        payloadRequest["full_name"] = "Luter Rinding"
        payloadRequest["customer_id"] = "2"

        disposable.add(
            viewModel.registerUser(payloadRequest).getBaseResult(loading, {
                toast(error.user)
            }, {
                message?.apply { toast(this) }
            })
        )
    }
}
 
