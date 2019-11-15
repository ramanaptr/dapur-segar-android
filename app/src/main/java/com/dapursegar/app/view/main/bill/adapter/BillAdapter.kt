package com.dapursegar.app.view.main.bill.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapursegar.app.R
import com.dapursegar.app.model.home.ProductItem
import kotlinx.android.synthetic.main.items_bill.view.*

class BillAdapter(
    private val listener: ProductItem.(String) -> Unit
) : RecyclerView.Adapter<BillAdapter.ViewHolder>() {

    companion object {
        const val PAY = "PAY"
        const val DETAIL = "DETAIL"
    }

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

    fun setData(items: MutableList<ProductItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivProduct = itemView.ivProduct
        private val btnPay = itemView.btnPay

        fun bindItem(data: ProductItem, listener: ProductItem.(String) -> Unit) {
            data.apply {
                ivProduct.setOnClickListener { listener(this, DETAIL) }
                btnPay.setOnClickListener { listener(this, PAY) }
            }
        }
    }
}