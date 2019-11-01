package com.dapursegar.app.view.detail

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.extension.settingToolbar
import com.dapursegar.app.view.detail.dialog.BSProductQuantity
import kotlinx.android.synthetic.main.detail_product_activity.*
import kotlinx.android.synthetic.main.detail_product_toolbar.*

/**
 * Created by Ramana on 20-Oct-19.
 */

class DetailProductActivity : BaseActivity() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Dummy Apel Malang"

    override fun setLayout(): Int = R.layout.detail_product_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        settingToolbar(tvTitle, "Dummy Apel Malang", toolbar)
        initComponent()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onClick() {
        val prices = mutableListOf<String>().apply {
            add("500 Gm - IDR 240.000")
            add("1 Kg - IDR 430.100")
            add("2 Kg - IDR 530.100")
            add("500 Gm - IDR 240.000")
            add("1 Kg - IDR 430.100")
            add("2 Kg - IDR 530.100")
            add("500 Gm - IDR 240.000")
            add("1 Kg - IDR 430.100")
            add("2 Kg - IDR 530.100")
            add("500 Gm - IDR 240.000")
            add("1 Kg - IDR 430.100")
            add("2 Kg - IDR 530.100")
            add("500 Gm - IDR 240.000")
            add("1 Kg - IDR 430.100")
            add("2 Kg - IDR 530.100")
            add("500 Gm - IDR 240.000")
            add("1 Kg - IDR 430.100")
            add("2 Kg - IDR 530.100")
            add("500 Gm - IDR 240.000")
            add("1 Kg - IDR 430.100")
            add("2 Kg - IDR 530.100")
        }
        btnCheckout.setOnClickListener {
            BSProductQuantity(prices){

            }.show(supportFragmentManager, "BSProductQuantity")
        }
    }

    private fun initComponent() {
    }
}