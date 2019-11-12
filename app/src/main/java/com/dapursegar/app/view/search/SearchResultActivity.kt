package com.dapursegar.app.view.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.extension.settingToolbar
import com.dapursegar.app.model.home.CategoryHome
import com.dapursegar.app.model.home.ProductItem
import com.dapursegar.app.view.search.adapter.SearchResultAdapter
import com.dapursegar.app.view.search.dialog.BSFilter
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.toolbar_search_result.*
import org.jetbrains.anko.startActivity

class SearchResultActivity : BaseActivity<SearchResultViewModel>() {

    private var searchResultAdapter: SearchResultAdapter? = null
    private val categoryHomeDummy = mutableListOf<CategoryHome>()

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "apel"

    override fun setLayout(): Int = R.layout.activity_search_result

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        settingToolbar(tvTitle, "apel", toolbar)
        setupAdapter()
        loadData()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_result, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                startActivity<SearchActivity>()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick() {
        val dummyFilter = mutableListOf<String>().apply {
            add("Most suitable")
            add("Highes price")
            add("Lowest price")
            add("Discount")
            add("A - Z")
            add("Z - A")
        }
        cvFilter.setOnClickListener {
            BSFilter(dummyFilter) {
                tvFilterType.text = it
            }.show(supportFragmentManager, "BSFilter")
        }
    }

    private fun setupAdapter() {
        searchResultAdapter = SearchResultAdapter { e, p -> {} }.apply { setData(loadData()) }
        rvProduct.apply {
            adapter = searchResultAdapter
            layoutManager = GridLayoutManager(context, 2)
            isNestedScrollingEnabled = false
        }
    }

    private fun loadData(): MutableList<ProductItem> {
        val products = mutableListOf<ProductItem>()
        for (category in 1..5) {
            products.add(ProductItem(R.drawable.durian, "Durian Bali"))
            categoryHomeDummy.add(CategoryHome("Choice product", products))
        }
        return products
    }

}
