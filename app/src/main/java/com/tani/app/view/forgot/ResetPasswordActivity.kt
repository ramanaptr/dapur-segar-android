package com.tani.app.view.forgot

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.tani.app.R
import com.tani.app.base.activity.BaseActivity
import com.tani.app.helper.hideError
import com.tani.app.helper.passwordState
import com.tani.app.helper.showError
import com.tani.app.helper.validatePassword
import kotlinx.android.synthetic.main.reset_password_activity.*
import java.lang.String.format

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
        btnEye.setOnClickListener { btnEye.passwordState(etPassword) }
        btnReEye.setOnClickListener { btnReEye.passwordState(etRePassword) }
        cvSubmit.setOnClickListener { validateForm() }
    }

    private fun validateForm(): Boolean {
        val password = etPassword.text.toString().trim()
        val rePassword = etRePassword.text.toString().trim()

        if (password.isEmpty()) {
            tilPassword.showError(getString(R.string.empty_password))
            return false
        } else {
            tilPassword.hideError()
        }

        if (!password.validatePassword(6)) {
            val passwordMsg = format(getString(R.string.invalid_minimal_input), 6)
            tilPassword.showError(passwordMsg)
            return false
        } else {
            tilPassword.hideError()
        }

        if (rePassword.isEmpty()) {
            tilRePassword.showError(getString(R.string.empty_repassword))
            return false
        } else {
            tilRePassword.hideError()
        }

        if (!rePassword.validatePassword(6)) {
            val passwordMsg = format(getString(R.string.invalid_minimal_input), 6)
            tilRePassword.showError(passwordMsg)
            return false
        } else {
            tilRePassword.hideError()
        }

        if (!rePassword.validatePassword(password)) {
            val passwordMsg = format("Re Password not match", 6)
            tilRePassword.showError(passwordMsg)
            return false
        } else {
            tilRePassword.hideError()
        }
        return true
    }

    private fun initComponent() {

    }
}