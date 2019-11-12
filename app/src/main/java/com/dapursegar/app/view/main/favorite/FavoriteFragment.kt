package com.dapursegar.app.view.main.favorite

import android.app.ActionBar
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.fragment.BaseFragment
import com.dapursegar.app.model.home.CategoryHome
import com.dapursegar.app.model.home.ProductItem
import com.dapursegar.app.view.detail.DetailProductActivity
import com.dapursegar.app.view.detail.dialog.BSProductQuantity
import com.dapursegar.app.view.main.favorite.adapter.FavoriteAdapter
import com.dapursegar.app.view.main.home.adapter.ProductAdapter
import com.dapursegar.app.view.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.toolbar_favorite.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Ramana on 19-Oct-19.
 */

class FavoriteFragment : BaseFragment<FavoriteViewModel>() {

    private var favoriteAdapter: FavoriteAdapter? = null

    private val categoryHomeDummy = mutableListOf<CategoryHome>()

    override fun setLayout(): Int = R.layout.fragment_favorite

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        tvTitle.text = getString(R.string.favorite_title)
        setupAdapter()
    }

    override fun onClick() {
        cvSearch.setOnClickListener { startActivity<SearchActivity>() }
    }

    private fun setupAdapter() {
        favoriteAdapter =
            FavoriteAdapter { state, product -> onClickFavorite(state, product) }.apply {
                setData(loadData())
            }
        rvFavorites.apply {
            adapter = favoriteAdapter
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

    private fun onClickFavorite(state: String, data: ProductItem) {
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
}