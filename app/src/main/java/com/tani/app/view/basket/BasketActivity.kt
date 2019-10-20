package com.tani.app.view.basket

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.tani.app.R
import com.tani.app.base.activity.BaseActivity

/**
 * Created by Ramana on 20-Oct-19.
 */
 
class BasketActivity  : BaseActivity() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = getString(R.string.basket_title)

    override fun setLayout(): Int = R.layout.basket_activity

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