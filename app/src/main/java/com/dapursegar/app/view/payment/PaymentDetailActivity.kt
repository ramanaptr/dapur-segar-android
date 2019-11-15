package com.dapursegar.app.view.payment

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.model.home.ProductItem
import com.dapursegar.app.view.cart.adapter.CartAdapter
import com.dapursegar.app.view.detail.EditProductCartActivity
import kotlinx.android.synthetic.main.activity_payment_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by Ramana on 20-Oct-19.
 */

class PaymentDetailActivity : BaseActivity<PaymentDetailViewModel>() {

    private var cartAdapter: CartAdapter? = null

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = getString(R.string.payment_detail)

    override fun setLayout(): Int = R.layout.activity_payment_detail

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
        setupAdapter()
        loadDummyData()
    }

    private fun initComponent() {
    }

    override fun onClick() {
        btnCancel.setOnClickListener {
            toast("Pembayaran dibatalkan")
            finish()
        }
        btnPay.setOnClickListener {
            startActivity<LoginAlertActivity>()
            overridePendingTransition(R.anim.scale_in_dialog, R.anim.scale_out_dialog)
        }
        tvEditAddress.setOnClickListener { toast("Akan muncul dialog/activity baru pada ubah alamat") }
        tvEditMethod.setOnClickListener { toast("Akan muncul dialog/activity baru pada ubah metode bayar") }
        tvCopy.setOnClickListener { toast("Salin berhasil") }
    }

    private fun setupAdapter() {
        rvProducts.apply {
            cartAdapter = CartAdapter { onClickProduct(this, it) }
            adapter = cartAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun onClickProduct(data: ProductItem, state: String) {
        data.apply {
            when (state) {
                CartAdapter.EDIT_DETAIL -> {
                    startActivity<EditProductCartActivity>()
                }
                CartAdapter.DELETE -> {
                    toast("Deleted from cart")
                }
            }
        }
    }

    private fun loadDummyData() {
        val dummy = arrayListOf<ProductItem>()
        for (i in 1..6) {
            dummy.add(ProductItem())
        }
        cartAdapter?.setData(false, dummy)
    }
}