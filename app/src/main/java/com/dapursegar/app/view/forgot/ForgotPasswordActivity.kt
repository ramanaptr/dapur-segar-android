package com.dapursegar.app.view.forgot

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*
import org.jetbrains.anko.startActivity

/**
 * Created by Ramana on 21-Sep-19.
 */

class ForgotPasswordActivity : BaseActivity<ForgotPasswordViewModel>() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Login"

    override fun setLayout(): Int = R.layout.activity_forgot_password

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onClick() {
        tvSkip.setOnClickListener { finish() }
        btnSend.setOnClickListener {
            startActivity<ResetPasswordActivity>()
        }
    }

    private fun initComponent() {

    }
}