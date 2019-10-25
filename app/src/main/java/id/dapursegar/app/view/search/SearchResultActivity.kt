package id.dapursegar.app.view.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import id.dapursegar.app.R
import id.dapursegar.app.base.activity.BaseActivity
import id.dapursegar.app.base.extension.settingToolbar
import id.dapursegar.app.model.home.CategoryHome
import id.dapursegar.app.model.home.ProductItem
import id.dapursegar.app.view.search.adapter.SearchResultAdapter
import kotlinx.android.synthetic.main.search_result_activity.*
import kotlinx.android.synthetic.main.search_result_toolbar.*
import org.jetbrains.anko.startActivity

class SearchResultActivity : BaseActivity() {

    private var searchResultAdapter: SearchResultAdapter? = null
    private val categoryHomeDummy = mutableListOf<CategoryHome>()

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "apel"

    override fun setLayout(): Int = R.layout.search_result_activity

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
