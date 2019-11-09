package com.dapursegar.app.view.main.favorite

import android.app.ActionBar
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.dialog.showWeightDialog
import com.dapursegar.app.base.fragment.BaseFragment
import com.dapursegar.app.model.home.CategoryHome
import com.dapursegar.app.model.home.ProductItem
import com.dapursegar.app.view.main.favorite.adapter.FavoriteAdapter
import com.dapursegar.app.view.search.SearchActivity
import kotlinx.android.synthetic.main.favorite_activity.*
import kotlinx.android.synthetic.main.toolbar_favorite.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by Ramana on 19-Oct-19.
 */

class FavoriteFragment : BaseFragment<FavoriteViewModel>() {

    private var favAdapter: FavoriteAdapter? = null

    private val categoryHomeDummy = mutableListOf<CategoryHome>()

    override fun setLayout(): Int = R.layout.favorite_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        tvTitle.text = getString(R.string.favorite_title)
        setupAdapter()
    }

    override fun onClick() {
        cvSearch.setOnClickListener { startActivity<SearchActivity>() }
    }

    private fun setupAdapter() {
        favAdapter = FavoriteAdapter { state, product -> onClickFavorite(state, product) }.apply {
            setData(loadData())
        }
        rvFavorites.apply {
            adapter = favAdapter
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

    private fun onClickFavorite(state: String, product: ProductItem) {
        when (state) {
            FavoriteAdapter.LOVE -> {
            }
            FavoriteAdapter.UNLOVE -> {
            }
            FavoriteAdapter.CHOOSE_WEIGHT -> {
                val prices = mutableListOf<String>().apply {
                    add("500 Gm - IDR 240.000")
                    add("1 Kg - IDR 430.100")
                }
                showWeightDialog(prices)
            }
            FavoriteAdapter.DETAIL -> {
            }
            FavoriteAdapter.SUBMIT -> {
            }
        }
    }
}