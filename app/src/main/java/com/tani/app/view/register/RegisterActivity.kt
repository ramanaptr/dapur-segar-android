package com.tani.app.view.register

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.tani.app.R
import com.tani.app.base.activity.BaseActivity
import kotlinx.android.synthetic.main.register_activity.*

/**
 * Created by Ramana on 21-Sep-19.
 */

class RegisterActivity : BaseActivity() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Login"

    override fun setLayout(): Int = R.layout.register_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onClick() {
        tvSkip.setOnClickListener { finish() }
        tvLogin.setOnClickListener { finish() }
    }

    private fun initComponent() {

    }
}
 
