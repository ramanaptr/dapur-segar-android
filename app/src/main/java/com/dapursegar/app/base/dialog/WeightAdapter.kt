package com.dapursegar.app.base.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dapursegar.app.R
import com.dapursegar.app.base.extension.colorText
import kotlinx.android.synthetic.main.items_weight_list.view.*
import org.jetbrains.anko.textColor

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

    fun setData(items: MutableList<String>) {
        this.items = items
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
                    tvWeightPrice.colorText(R.color.white)
                    ivCheck.setImageResource(R.drawable.ic_checked)
                    rlWeight.setBackgroundResource(R.drawable.square_green_light_solid)
                    listener(data)
                }
            }
        }
    }
}