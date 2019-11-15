package com.dapursegar.app.view.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapursegar.app.R
import com.dapursegar.app.model.home.ProductItem
import kotlinx.android.synthetic.main.items_product_horizontal.view.*

class ProductAdapter(
    private val listener: ProductItem.(String) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

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
                .inflate(R.layout.items_product_horizontal, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    fun setData(items: MutableList<ProductItem>) {
        this.items = items
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
        private val btnAdd = itemView.btnAdd

        fun bindItem(data: ProductItem, listener: ProductItem.(String) -> Unit) {
            data.apply {
                ivThumbnail.setImageResource(image)
                rlFavorite.setOnClickListener {
                    isLove = if (isLove) {
                        ivLove.setImageResource(R.drawable.ic_loved)
                        listener(this, LOVED)
                        false
                    } else {
                        ivLove.setImageResource(R.drawable.ic_unloved)
                        listener(this, UNLOVED)
                        true
                    }
                }
                itemView.setOnClickListener { listener(this, DETAIL) }
                btnAdd.setOnClickListener { listener(this, CART) }
                // tvWeight.setOnClickListener { listener(this, SPINNER) }
            }
        }
    }
}