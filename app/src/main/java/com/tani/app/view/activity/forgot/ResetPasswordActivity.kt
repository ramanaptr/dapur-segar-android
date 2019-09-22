package com.tani.app.view.activity.forgot

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.tani.app.R
import com.tani.app.base.activity.BaseActivity
import com.tani.app.helper.Validator
import com.tani.app.helper.ViewUtils
import kotlinx.android.synthetic.main.reset_password_activity.*

/**
 * Created by Ramana on 21-Sep-19.
 */

class ResetPasswordActivity : BaseActivity() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Login"

    override fun setLayout(): Int = R.layout.reset_password_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onClick() {
        tvSkip.setOnClickListener { finish() }
        btnEye.setOnClickListener { ViewUtils.passwordState(it, etPassword) }
        btnReEye.setOnClickListener { ViewUtils.passwordState(it, etRePassword) }
        cvSubmit.setOnClickListener { validateForm() }
    }

    private fun validateForm() {
        val password = etPassword.text.toString()
        val rePassword = etRePassword.text.toString()
        when {
            password.isEmpty() -> Validator.showErrorTextInput(
                tilPassword,
                "Password can't be empty"
            )
            rePassword.isEmpty() -> Validator.showErrorTextInput(
                tilRePassword,
                "Re Password can't be empty"
            )
            else -> {
                Validator.hideErrorTextInput(tilPassword)
                Validator.hideErrorTextInput(tilRePassword)
            }
        }
    }

    private fun initComponent() {

    }
}