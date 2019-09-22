package com.tani.app.view.activity.forgot

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.tani.app.R
import com.tani.app.base.activity.BaseActivity
import kotlinx.android.synthetic.main.forgot_password_activity.*
import org.jetbrains.anko.startActivity

/**
 * Created by Ramana on 21-Sep-19.
 */

class ForgotPasswordActivity : BaseActivity() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Login"

    override fun setLayout(): Int = R.layout.forgot_password_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onClick() {
        tvSkip.setOnClickListener { finish() }
        cvSubmit.setOnClickListener {
            startActivity<ResetPasswordActivity>()
        }
    }

    private fun initComponent() {

    }
}