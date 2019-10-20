package id.dapursegar.app.view.main

import android.os.Bundle
import android.util.SparseArray
import android.view.KeyEvent
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import id.dapursegar.app.R
import id.dapursegar.app.base.activity.BaseActivity
import id.dapursegar.app.base.adapter.BaseMainPagerAdapter
import id.dapursegar.app.base.extension.handleBackFromMain
import id.dapursegar.app.base.extension.setupTablayout
import id.dapursegar.app.view.main.bill.BillFragment
import id.dapursegar.app.view.main.favorite.FavoriteFragment
import id.dapursegar.app.view.main.home.HomepageFragment
import id.dapursegar.app.view.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Created by Ramana on 22-Sep-19.
 */

class MainActivity : BaseActivity() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Login"

    override fun setLayout(): Int = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onClick() {

    }

    private fun initComponent() {
        setupPager()
    }

    private fun setupPager() {
        vpMain?.apply {
            val fragmentAdapter = BaseMainPagerAdapter(supportFragmentManager, addFragments())
            adapter = fragmentAdapter
            offscreenPageLimit = addFragments().size()
        }
        tlMain?.apply {
            setupWithViewPager(vpMain)
            setupTablayout(vpMain) {}
            isTabIndicatorFullWidth = false
        }
    }

    private fun addFragments(): SparseArray<Fragment> {
        val fragments = SparseArray<Fragment>()
        fragments.put(0, HomepageFragment())
        fragments.put(1, BillFragment())
        fragments.put(2, FavoriteFragment())
        fragments.put(3, ProfileFragment())
        return fragments
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return handleBackFromMain(keyCode, vpMain)
    }
}