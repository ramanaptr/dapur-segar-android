package com.dapursegar.app.view.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapursegar.app.R
import com.dapursegar.app.base.extension.gone
import com.dapursegar.app.model.home.ProductItem
import kotlinx.android.synthetic.main.items_product_cart.view.*

class CartAdapter(
    private val listener: ProductItem.(String) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    companion object {
        const val DELETE = "DELETE"
        const val EDIT_DETAIL = "EDIT_DETAIL"
    }

    private var isCart = false
    private var items = mutableListOf<ProductItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.items_product_cart, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(isCart, items[position], listener)
    }

    fun setData(isCart: Boolean, items: MutableList<ProductItem>) {
        this.isCart = isCart
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvEditDetail = itemView.tvEditDetail
        private val btnDelete = itemView.btnDelete

        fun bindItem(
            isCart: Boolean,
            data: ProductItem,
            listener: ProductItem.(String) -> Unit
        ) {
            data.apply {
                if (isCart) {
                    btnDelete.setOnClickListener { listener(this, DELETE) }
                    tvEditDetail.setOnClickListener { listener(this, EDIT_DETAIL) }
                } else {
                    btnDelete.gone()
                    tvEditDetail.gone()
                }
            }
        }
    }
}