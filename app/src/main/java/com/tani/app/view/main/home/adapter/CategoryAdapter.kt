package com.tani.app.view.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tani.app.R
import com.tani.app.model.home.CategoryHome
import com.tani.app.model.home.ProductHome
import kotlinx.android.synthetic.main.items_category.view.*

class CategoryAdapter(
    private val moreListener: (CategoryHome) -> Unit,
    private val listener: (ProductHome) -> Unit
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

    fun setData(menus: MutableList<CategoryHome>) {
        this.items = menus
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvCategory = itemView.tvCategory
        private val rvProduct = itemView.rvProduct
        private val tvMore = itemView.tvMore

        fun bindItem(
            data: CategoryHome,
            moreListener: (CategoryHome) -> Unit,
            listener: (ProductHome) -> Unit
        ) {
            data.apply {
                tvCategory.text = categoryName
                tvMore.setOnClickListener { moreListener(data) }
                rvProduct.apply {
                    adapter = ProductAdapter { listener(it) }.apply { setData(products) }
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