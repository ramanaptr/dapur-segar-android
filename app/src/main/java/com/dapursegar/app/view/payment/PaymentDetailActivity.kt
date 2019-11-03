package com.dapursegar.app.view.payment

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity

/**
 * Created by Ramana on 20-Oct-19.
 */
 
class PaymentDetailActivity : BaseActivity<PaymentDetailViewModel>(){

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = getString(R.string.payment_detail)

    override fun setLayout(): Int = R.layout.payment_detail

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onClick() {

    }

    private fun initComponent() {
    }
}