package com.tani.app.view.activity.main.home

import android.app.ActionBar
import android.os.Build
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tani.app.R
import com.tani.app.base.adapter.BannersAdapter
import com.tani.app.base.fragment.BaseFragment
import com.tani.app.helper.TimerHelper
import com.tani.app.model.home.CategoryHome
import com.tani.app.model.home.MenuHome
import com.tani.app.model.home.ProductHome
import com.tani.app.view.activity.main.home.adapter.CategoryAdapter
import com.tani.app.view.activity.main.home.adapter.MenuAdapter
import kotlinx.android.synthetic.main.home_toolbar.*
import kotlinx.android.synthetic.main.homepage_fragment.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class HomepageFragment : BaseFragment() {

    private var bannersAdapter: BannersAdapter? = null
    private var menuAdapter: MenuAdapter? = null
    private var categoryAdapter: CategoryAdapter? = null
    private val bannersDummy = mutableListOf<Int>().apply {
        add(R.drawable.durian)
        add(R.drawable.fresh_fruit)
    }
    private val categoryHomeDummy = mutableListOf<CategoryHome>()

    override fun setLayout(): Int = R.layout.homepage_fragment

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            homeToolbar.elevation = 0f
        }
        loadData()
        setupAdapter()
    }

    private fun loadData() {
        val products = mutableListOf<ProductHome>()
        for (category in 1..5) {
            products.add(ProductHome(R.drawable.durian, "Durian Bali"))
            categoryHomeDummy.add(CategoryHome("Choice product", products))
        }
    }

    override fun onClick() {

    }

    private fun setupAdapter() {
        vpSlide.apply {
            bannersAdapter = BannersAdapter().apply { setBanners(bannersDummy) }
            adapter = bannersAdapter
            pageMargin = 20
            TimerHelper.autoSlide(this, bannersDummy.size, 10)
        }
        piv.apply {
            radius = 4
            padding = 8
        }
        rvMenus.apply {
            menuAdapter = MenuAdapter { onClickMenu(it) }.apply { setData(MenuHome.getMenus()) }
            adapter = menuAdapter
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(this.context, 3)
        }
        categoryAdapter = CategoryAdapter({ onClickMore(it) }, { onClickCategory(it) })
        rvCategory.apply {
            adapter = categoryAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this.context)
        }
        categoryAdapter?.setData(categoryHomeDummy)
    }

    private fun onClickMore(data: CategoryHome) {
        context?.toast(data.categoryName)
    }

    private fun onClickCategory(data: ProductHome) {
        data.apply {
            context?.toast("Adding $name")
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
