package com.dapursegar.app.view.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dapursegar.app.R
import com.dapursegar.app.model.home.CategoryHome
import com.dapursegar.app.model.home.ProductItem
import kotlinx.android.synthetic.main.items_category.view.*

class CategoryAdapter(
    private val moreListener: (CategoryHome) -> Unit,
    private val listener: ProductItem.(String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var items: MutableList<CategoryHome> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.items_category, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], moreListener, listener)
    }

    fun setData(items: MutableList<CategoryHome>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvCategory = itemView.tvCategory
        private val rvProduct = itemView.rvProduct
        private val tvMore = itemView.tvMore

        fun bindItem(
            data: CategoryHome,
            moreListener: (CategoryHome) -> Unit,
            listener: ProductItem.(String) -> Unit
        ) {
            data.apply {
                tvCategory.text = categoryName
                tvMore.setOnClickListener { moreListener(data) }
                rvProduct.apply {
                    adapter = ProductAdapter { state ->
                        listener(this, state)
                    }.apply { setData(products) }
                    isNestedScrollingEnabled = false
                    layoutManager = LinearLayoutManager(
                        this.context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                }
            }
        }
    }
}