package com.dapursegar.app.view.login

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.extension.*
import com.dapursegar.app.view.forgot.ForgotPasswordActivity
import com.dapursegar.app.view.main.MainActivity
import com.dapursegar.app.view.register.RegisterActivity
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.lang.String.format

/**
 * Created by Ramana on 21-Sep-19.
 */

class LoginActivity : BaseActivity<LoginViewModel>() {

    private lateinit var password: String
    private lateinit var username: String

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Login"

    override fun setLayout(): Int = R.layout.login_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onClick() {
        tvSkip.setOnClickListener { startActivity<MainActivity>() }
        tvForgot.setOnClickListener { startActivity<ForgotPasswordActivity>() }
        tvRegister.setOnClickListener { startActivity<RegisterActivity>() }
        btnEye.setOnClickListener { btnEye.passwordState(etPassword) }
        cvSubmit.setOnClickListener {
             toast("${isValidateForm()} ")
        }
    }

    private fun isValidateForm(): Boolean {
        username = etUsername.text.toString().trim()
        password = etPassword.text.toString().trim()

        if (username.isEmpty()) return tilUsername.showError(getString(R.string.empty_username))
        else tilUsername.hideError()

        if (!username.validateEmail()) return tilUsername.showError(getString(R.string.invalid_email))
        else tilUsername.hideError()

        if (password.isEmpty()) return tilPassword.showError(getString(R.string.empty_password))
        else tilPassword.hideError()

        if (!password.validatePassword(6)) {
            val passwordMsg = format(getString(R.string.invalid_minimal_input), 6)
            return tilPassword.showError(passwordMsg)
        } else {
            tilPassword.hideError()
        }
        return true
    }


    private fun initComponent() {

    }
}
