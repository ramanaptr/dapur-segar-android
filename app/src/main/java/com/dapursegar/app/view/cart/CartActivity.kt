package com.dapursegar.app.view.cart

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.model.home.ProductItem
import com.dapursegar.app.view.cart.adapter.CartAdapter
import com.dapursegar.app.view.detail.EditProductCartActivity
import kotlinx.android.synthetic.main.activity_cart.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by Ramana on 20-Oct-19.
 */

class CartActivity : BaseActivity<CartViewModel>() {

    private var cartAdapter: CartAdapter? = null
    private val cartData = mutableListOf<ProductItem>()

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = getString(R.string.cart_title)

    override fun setLayout(): Int = R.layout.activity_cart

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

    }

    private fun initComponent() {
    }

    private fun setupAdapter() {
        rvChoosePackage.apply {
            cartAdapter = CartAdapter { onClickProduct(this, it) }
            adapter = cartAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun onClickProduct(productItem: ProductItem, state: String) {
        when (state) {
            CartAdapter.EDIT_DETAIL -> {
                startActivity<EditProductCartActivity>()
            }
            CartAdapter.DELETE -> {
                toast("Deleted from cart")
            }
        }
    }

    private fun loadDummyData() {
        for (i in 1..5) {
            cartData.add(ProductItem())
        }
        cartAdapter?.setData(cartData)
        btnCheckout.text = "Beli (${cartData.size})"
    }

}