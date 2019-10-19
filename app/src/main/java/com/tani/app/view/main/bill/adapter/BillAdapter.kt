package com.tani.app.view.main.bill.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tani.app.R
import com.tani.app.model.home.ProductItem
import kotlinx.android.synthetic.main.items_product.view.*

class BillAdapter(
    private val listener: (ProductItem) -> Unit
) : RecyclerView.Adapter<BillAdapter.ViewHolder>() {

    private var items: MutableList<ProductItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.items_bill, parent, false)
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

        private val ivThumbnail = itemView.ivThumbnail

        fun bindItem(data: ProductItem, listener: (ProductItem) -> Unit) {
            data.apply {
            }
        }
    }
}