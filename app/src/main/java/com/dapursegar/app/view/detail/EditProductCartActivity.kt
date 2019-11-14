package com.dapursegar.app.view.detail

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.dialog.WeightAdapter
import kotlinx.android.synthetic.main.activity_edit_product_cart.*

/**
 * Created by Ramana on 20-Oct-19.
 */

class EditProductCartActivity : BaseActivity<EditProductCartViewModel>() {

    private var weightAdapter: WeightAdapter? = null

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Ubah Pesanan"

    override fun setLayout(): Int = R.layout.activity_edit_product_cart

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
        setupAdapter()
        loadDummyData()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onClick() {
        btnCancel.setOnClickListener { finish() }
        btnSave.setOnClickListener { finish() }
    }

    private fun initComponent() {
    }

    private fun setupAdapter() {
        rvChoosePackage.apply {
            weightAdapter = WeightAdapter { viewState(true) }
            adapter = weightAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun viewState(state: Boolean) {

    }

    private fun loadDummyData() {
        val prices = mutableListOf<String>().apply {
            add("500 Gm - IDR 40.000")
            add("1 Kg - IDR 130.100")
            add("2 Kg - IDR 230.100")
            add("3 Kg - IDR 340.000")
        }
        weightAdapter?.setData(prices)
    }
}