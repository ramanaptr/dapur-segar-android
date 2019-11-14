package com.dapursegar.app.view.detail

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity

/**
 * Created by Ramana on 20-Oct-19.
 */
 
class EditDetailProductActivity : BaseActivity<EditDetailProductViewModel>() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = getString(R.string.cart_title)

    override fun setLayout(): Int = R.layout.activity_edit_detail

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