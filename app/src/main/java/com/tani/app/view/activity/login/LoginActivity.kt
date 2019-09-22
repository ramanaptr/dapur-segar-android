package com.tani.app.view.activity.login

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.tani.app.R
import com.tani.app.base.activity.BaseActivity
import com.tani.app.helper.Validator
import com.tani.app.helper.ViewUtils
import com.tani.app.view.activity.forgot.ForgotPasswordActivity
import com.tani.app.view.activity.main.MainActivity
import com.tani.app.view.activity.register.RegisterActivity
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.startActivity

/**
 * Created by Ramana on 21-Sep-19.
 */

class LoginActivity : BaseActivity() {

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
        btnEye.setOnClickListener { ViewUtils.passwordState(it, etPassword) }
        cvSubmit.setOnClickListener { validateForm() }
    }

    private fun validateForm() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        when {
            username.isEmpty() -> Validator.showErrorTextInput(
                tilUsername,
                "Username can't be empty"
            )
            password.isEmpty() -> Validator.showErrorTextInput(
                tilPassword,
                "Password can't be empty"
            )
            else -> {
                Validator.hideErrorTextInput(tilUsername)
                Validator.hideErrorTextInput(tilPassword)
            }
        }
    }

    private fun initComponent() {

    }
}
