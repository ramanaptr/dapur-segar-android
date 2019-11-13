package com.dapursegar.app.view.detail

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.adapter.BannersAdapter
import com.dapursegar.app.base.extension.settingToolbar
import com.dapursegar.app.model.home.ProductItem
import com.dapursegar.app.view.detail.dialog.BSProductQuantity
import com.dapursegar.app.view.main.home.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.activity_detail_product.tvWeightPrice
import kotlinx.android.synthetic.main.default_add_quantity.*
import kotlinx.android.synthetic.main.detail_product_toolbar.*
import kotlinx.android.synthetic.main.dialog_product_quantity.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by Ramana on 20-Oct-19.
 */

class DetailProductActivity : BaseActivity<DetailProductViewModel>() {

    private var quantity = 1
    private var bannersAdapter: BannersAdapter? = null
    private val bannersDummy = mutableListOf<Int>().apply {
        add(R.drawable.durian)
        add(R.drawable.fresh_fruit)
    }

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Dummy Apel Malang"

    override fun setLayout(): Int = R.layout.activity_detail_product

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        settingToolbar(tvTitle, "Dummy Apel Malang", toolbar)
        initComponent()
        setupAdapter()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun loadData(): MutableList<ProductItem> {
        val products = mutableListOf<ProductItem>()
        for (category in 1..5) {
            products.add(ProductItem(R.drawable.durian, "Durian Bali"))
        }
        return products
    }

    override fun onClick() {
        tvPlus.setBackgroundResource(R.drawable.rounded_right_green)
        tvMinus.setOnClickListener {
            quantity--
            etQuantity.setText(quantity.toString())
            tvWeightPrice.text = "IDR 420.100/ 1 Kg (${quantity}x)"
        }
        tvPlus.setOnClickListener {
            quantity++
            etQuantity.setText(quantity.toString())
            tvWeightPrice.text = "IDR 420.100/ 1 Kg (${quantity}x)"
        }
    }

    private fun initComponent() {
    }

    private fun setupAdapter() {
        rvSimilarProduct.apply {
            adapter = ProductAdapter { onClickProduct(this, it) }.apply { setData(loadData()) }
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        vpSlide.apply {
            bannersAdapter = BannersAdapter(BannersAdapter.PRODUCT).apply {
                setBanners(bannersDummy)
            }
            adapter = bannersAdapter
            piv.apply {
                radius = 4
                padding = 8
            }
        }
    }

    private fun onClickProduct(data: ProductItem, state: String) {
        data.apply {
            when (state) {
                ProductAdapter.CART -> {
                    val prices = mutableListOf<String>().apply {
                        add("500 Gm - IDR 40.000")
                        add("1 Kg - IDR 130.100")
                        add("2 Kg - IDR 230.100")
                        add("3 Kg - IDR 340.000")
                    }
                    BSProductQuantity(prices) {
                        toast(it)
                    }.show(supportFragmentManager, "BSProductQuantity")
                }
                ProductAdapter.DETAIL -> {
                    startActivity<DetailProductActivity>()
                }
                ProductAdapter.LOVED -> {
                    toast("Produk telah di favorit")
                }
                ProductAdapter.UNLOVED -> {
                    toast("Favorit telah di hapus")
                }
            }
        }
    }
}