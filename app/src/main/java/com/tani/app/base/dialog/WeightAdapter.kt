package com.tani.app.base.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tani.app.R
import kotlinx.android.synthetic.main.items_weight_list.view.*

class WeightAdapter(
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<WeightAdapter.ViewHolder>() {

    private var items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.items_weight_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    fun setData(menus: MutableList<String>) {
        this.items = menus
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rlWeight = itemView.rlWeight
        private val tvWeightPrice = itemView.tvWeightPrice
        private val ivCheck = itemView.ivCheck


        fun bindItem(data: String, listener: (String) -> Unit) {
            data.apply {
                tvWeightPrice.text = data
                rlWeight.setOnClickListener {
                    rlWeight.setBackgroundResource(R.drawable.rounded_green_solid)
                    listener(data)
                }
            }
        }
    }
}