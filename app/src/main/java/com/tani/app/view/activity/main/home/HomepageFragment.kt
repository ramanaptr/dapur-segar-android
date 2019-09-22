package com.tani.app.view.activity.main.home

import android.app.ActionBar
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.tani.app.R
import com.tani.app.base.adapter.BannersAdapter
import com.tani.app.base.fragment.BaseFragment
import com.tani.app.helper.TimerHelper
import com.tani.app.model.home.MenuHome
import com.tani.app.view.activity.main.home.adapter.MenuAdapter
import kotlinx.android.synthetic.main.homepage_fragment.*
import org.jetbrains.anko.support.v4.toast
import java.util.concurrent.TimeUnit

class HomepageFragment : BaseFragment() {

    private var bannersAdapter: BannersAdapter? = null
    private var menuAdapter: MenuAdapter? = null
    private val banners = mutableListOf<Int>().apply {
        add(R.drawable.durian)
        add(R.drawable.fresh_fruit)
    }

    override fun setLayout(): Int = R.layout.homepage_fragment

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        setupAdapter()
    }

    override fun onClick() {

    }

    private fun setupAdapter() {
        vpSlide.apply {
            bannersAdapter = BannersAdapter().apply { setBanners(banners) }
            adapter = bannersAdapter
            pageMargin = 20
            TimerHelper.autoSlide(this, banners.size, TimeUnit.SECONDS.toMillis(10))
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
