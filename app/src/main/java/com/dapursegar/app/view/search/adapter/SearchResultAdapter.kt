package com.dapursegar.app.view.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapursegar.app.R
import com.dapursegar.app.model.home.ProductItem
import kotlinx.android.synthetic.main.items_product_vertical.view.*

class SearchResultAdapter(
    private val listener: (String, ProductItem) -> Unit
) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    companion object {
        const val LOVE = "love"
        const val UNLOVE = "unlove"
        const val SUBMIT = "submit"
        const val DETAIL = "detail"
        const val CHOOSE_WEIGHT = "choose_weight"
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
        private val etQuantity = itemView.etQuantity
        private val btnAdd = itemView.btnAdd

        fun bindItem(data: ProductItem, listener: (String, ProductItem) -> Unit) {
            data.apply {
                ivThumbnail.setImageResource(image)
                rlFavorite.setOnClickListener {
                    isLove = if (isLove) {
                        ivLove.setImageResource(R.drawable.ic_loved)
                        listener(LOVE, this)
                        false
                    } else {
                        ivLove.setImageResource(R.drawable.ic_unloved)
                        listener(UNLOVE, this)
                        true
                    }
                }
                btnAdd.setOnClickListener { listener(SUBMIT, this) }
                ivThumbnail.setOnClickListener { listener(DETAIL, this) }
                tvWeight.setOnClickListener { listener(CHOOSE_WEIGHT, this) }
            }
        }
    }
}