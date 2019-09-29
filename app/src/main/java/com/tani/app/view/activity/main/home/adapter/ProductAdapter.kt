package com.tani.app.view.activity.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tani.app.R
import com.tani.app.model.home.ProductHome
import kotlinx.android.synthetic.main.items_product.view.*

class ProductAdapter(
    private val listener: (ProductHome) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var items: MutableList<ProductHome> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.items_product, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    fun setData(menus: MutableList<ProductHome>) {
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
        private val cvSubmit = itemView.cvSubmit

        fun bindItem(data: ProductHome, listener: (ProductHome) -> Unit) {
            data.apply {
                ivThumbnail.setImageResource(image)
                rlFavorite.setOnClickListener {
                    isLove = if (isLove) {
                        ivLove.setImageResource(R.drawable.ic_loved)
                        false
                    } else {
                        ivLove.setImageResource(R.drawable.ic_loved_off)
                        true
                    }
                }
                cvSubmit.setOnClickListener { listener(this) }
                ivThumbnail.setOnClickListener { listener(this) }
            }
        }
    }
}