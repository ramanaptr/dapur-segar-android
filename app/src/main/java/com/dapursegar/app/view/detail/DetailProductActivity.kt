package com.dapursegar.app.view.detail

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.adapter.BannersAdapter
import com.dapursegar.app.base.dialog.WeightAdapter
import com.dapursegar.app.base.extension.settingToolbar
import com.dapursegar.app.model.home.ProductItem
import com.dapursegar.app.view.detail.dialog.BSProductQuantity
import com.dapursegar.app.view.main.home.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.default_add_quantity.*
import kotlinx.android.synthetic.main.detail_product_toolbar.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by Ramana on 20-Oct-19.
 */

class DetailProductActivity : BaseActivity<DetailProductViewModel>() {

    private var quantity = 1
    private var bannersAdapter: BannersAdapter? = null
    private var productAdapter: ProductAdapter? = null
    private var weightAdapter: WeightAdapter? = null
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
        loadData()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun loadData() {
        val products = mutableListOf<ProductItem>()
        for (category in 1..5) {
            products.add(ProductItem(R.drawable.durian, "Durian Bali"))
        }
        productAdapter?.setData(products)

        val prices = mutableListOf<String>().apply {
            add("500 Gm - IDR 40.000")
            add("1 Kg - IDR 130.100")
            add("2 Kg - IDR 230.100")
            add("3 Kg - IDR 340.000")
        }
        weightAdapter?.setData(prices)

        bannersAdapter?.setBanners(bannersDummy)
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
        vpSlide.apply {
            bannersAdapter = BannersAdapter(BannersAdapter.PRODUCT)
            adapter = bannersAdapter
            piv.apply {
                radius = 4
                padding = 8
            }
        }
        rvSimilarProduct.apply {
            productAdapter =
                ProductAdapter { onClickProduct(this, it) }
            adapter = productAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        rvProductOrder.apply {
            weightAdapter = WeightAdapter { viewState(true) }
            adapter = weightAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun viewState(state: Boolean) {

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