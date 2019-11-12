package com.dapursegar.app.view.detail

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.extension.settingToolbar
import com.dapursegar.app.view.detail.dialog.BSProductQuantity
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.detail_product_toolbar.*

/**
 * Created by Ramana on 20-Oct-19.
 */

class DetailProductActivity : BaseActivity<DetailProductViewModel>() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Dummy Apel Malang"

    override fun setLayout(): Int = R.layout.activity_detail_product

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        settingToolbar(tvTitle, "Dummy Apel Malang", toolbar)
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