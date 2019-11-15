package com.dapursegar.app.view.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapursegar.app.R
import com.dapursegar.app.view.search.model.TagItem
import kotlinx.android.synthetic.main.items_tag.view.*

class TagAdapter(
    private val listener: (TagItem) -> Unit
) : RecyclerView.Adapter<TagAdapter.ViewHolder>() {

    private var items: MutableList<TagItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.items_tag, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    fun setData(items: MutableList<TagItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTagName = itemView.tvTagName

        fun bindItem(data: TagItem, listener: (TagItem) -> Unit) {
            tvTagName.text = data.tagName
            tvTagName.setOnClickListener { listener(data) }
        }
    }
}