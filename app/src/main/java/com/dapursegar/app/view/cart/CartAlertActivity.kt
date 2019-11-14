package com.dapursegar.app.view.cart

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity

/**
 * Created by Ramana on 20-Oct-19.
 */

class CartAlertActivity : BaseActivity<CartAlertViewModel>() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = getString(R.string.cart_title)

    override fun setLayout(): Int = R.layout.activity_cart_alert

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onClick() {

    }

    private fun initComponent() {
    }
}