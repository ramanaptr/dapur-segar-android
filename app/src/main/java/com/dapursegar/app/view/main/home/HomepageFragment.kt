package com.dapursegar.app.view.main.home

import android.app.ActionBar
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.adapter.BannersAdapter
import com.dapursegar.app.base.fragment.BaseFragment
import com.dapursegar.app.helper.TimerHelper
import com.dapursegar.app.model.home.CategoryHome
import com.dapursegar.app.model.home.MenuHome
import com.dapursegar.app.model.home.ProductItem
import com.dapursegar.app.view.cart.CartActivity
import com.dapursegar.app.view.detail.DetailProductActivity
import com.dapursegar.app.view.detail.dialog.BSProductQuantity
import com.dapursegar.app.view.main.home.adapter.CategoryAdapter
import com.dapursegar.app.view.main.home.adapter.MenuAdapter
import com.dapursegar.app.view.main.home.adapter.ProductAdapter
import com.dapursegar.app.view.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_homepage.*
import kotlinx.android.synthetic.main.toolbar_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class HomepageFragment : BaseFragment<HomepageViewModel>() {

    private var bannersAdapter: BannersAdapter? = null
    private var menuAdapter: MenuAdapter? = null
    private var categoryAdapter: CategoryAdapter? = null
    private val bannersDummy = mutableListOf<Int>().apply {
        add(R.drawable.durian)
        add(R.drawable.fresh_fruit)
    }
    private val categoryHomeDummy = mutableListOf<CategoryHome>()

    override fun setLayout(): Int = R.layout.fragment_homepage

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        loadData()
        setupAdapter()
    }

    private fun loadData() {
        val products = mutableListOf<ProductItem>()
        for (category in 1..5) {
            products.add(ProductItem(R.drawable.durian, "Durian Bali"))
            categoryHomeDummy.add(CategoryHome("Choice product", products))
        }
    }

    override fun onClick() {
        cvSearch.setOnClickListener { startActivity<SearchActivity>() }
        btnCart.setOnClickListener { startActivity<CartActivity>() }
    }

    private fun setupAdapter() {
        vpSlide.apply {
            bannersAdapter = BannersAdapter(BannersAdapter.PROMO).apply { setBanners(bannersDummy) }
            adapter = bannersAdapter
            pageMargin = 20
            TimerHelper.autoSlide(this, bannersDummy.size, 10)
            piv.apply {
                radius = 4
                padding = 8
            }
        }
        rvMenus.apply {
            menuAdapter = MenuAdapter { onClickMenu(it) }.apply { setData(MenuHome.getMenus()) }
            adapter = menuAdapter
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(this.context, 3)
        }
        categoryAdapter = CategoryAdapter({ onClickMore(it) }, { onClickProduct(this, it) })
        rvCategory.apply {
            adapter = categoryAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this.context)
        }
        categoryAdapter?.setData(categoryHomeDummy)
    }

    private fun onClickMore(data: CategoryHome) {
        toast(data.categoryName)
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
                    }.show(childFragmentManager, "BSProductQuantity")
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

    private fun onClickMenu(menu: MenuHome) {
        when (menu.menuIcon) {
            R.drawable.ic_fruit -> {
                toast(menu.menuName)
            }
            R.drawable.ic_vegetable -> {
                toast(menu.menuName)
            }
            R.drawable.ic_riceandseeds -> {
                toast(menu.menuName)
            }
            R.drawable.ic_poultrynegg -> {
                toast(menu.menuName)
            }
            R.drawable.ic_fish -> {
                toast(menu.menuName)
            }
            R.drawable.ic_wholesale -> {
                toast(menu.menuName)
            }
        }
    }
}
