package com.dapursegar.app.view.main.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapursegar.app.R
import com.dapursegar.app.model.home.ProductItem
import kotlinx.android.synthetic.main.items_product_vertical.view.*

class FavoriteAdapter(
    private val listener: (String, ProductItem) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    companion object {
        const val CART = "CART"
        const val DETAIL = "DETAIL"
        const val SPINNER = "SPINNER"
        const val LOVED = "LOVED"
        const val UNLOVED = "UNLOVED"
    }

    private var items: MutableList<ProductItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.items_product_vertical, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    fun setData(menus: MutableList<ProductItem>) {
        this.items = menus
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var isLove = true
        private val ivThumbnail = itemView.ivThumbnail
        private val rlFavorite = itemView.rlFavorite
        private val ivLove = itemView.ivLove
        private val tvStoreName = itemView.tvStoreName
        private val tvProductName = itemView.tvProductName
        private val tvQuantity = itemView.tvQuantity
        private val tvPrice = itemView.tvPrice
        private val tvWeight = itemView.tvWeight
        private val etQuantity = itemView.etQuantity
        private val btnAdd = itemView.btnAdd

        fun bindItem(data: ProductItem, listener: (String, ProductItem) -> Unit) {
            data.apply {
                ivThumbnail.setImageResource(image)
                rlFavorite.setOnClickListener {
                    isLove = if (isLove) {
                        ivLove.setImageResource(R.drawable.ic_loved)
                        listener(LOVED, this)
                        false
                    } else {
                        ivLove.setImageResource(R.drawable.ic_unloved)
                        listener(UNLOVED, this)
                        true
                    }
                }
                btnAdd.setOnClickListener { listener(CART, this) }
                ivThumbnail.setOnClickListener { listener(DETAIL, this) }
                // tvWeight.setOnClickListener { listener(SPINNER, this) }
            }
        }
    }
}