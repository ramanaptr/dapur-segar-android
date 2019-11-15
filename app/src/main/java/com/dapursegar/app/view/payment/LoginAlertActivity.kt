package com.dapursegar.app.view.payment

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.view.login.LoginActivity
import com.dapursegar.app.view.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_cart_alert.*
import org.jetbrains.anko.startActivity

/**
 * Created by Ramana on 20-Oct-19.
 */

class LoginAlertActivity : BaseActivity<LoginAlertViewModel>() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = getString(R.string.cart_title)

    override fun setLayout(): Int = R.layout.activity_cart_alert

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        overridePendingTransition(R.anim.scale_out_dialog, R.anim.scale_in_dialog)
        return super.onSupportNavigateUp()
    }

    override fun onClick() {
        btnLogin.setOnClickListener { startActivity<LoginActivity>() }
        btnRegister.setOnClickListener {
            // startActivity(intentFor<RegisterActivity>("BACK_LOGIN" to true).clearTop())
            startActivity<RegisterActivity>("BACK_LOGIN" to true)
            finishAffinity()
        }
    }

    private fun initComponent() {
    }
}