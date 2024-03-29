package com.dapursegar.app.view.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapursegar.app.R
import com.dapursegar.app.model.home.MenuHome
import kotlinx.android.synthetic.main.items_home_menu.view.*

class MenuAdapter(
    private val listener: (MenuHome) -> Unit
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private var items: MutableList<MenuHome> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.items_home_menu, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    fun setData(items: MutableList<MenuHome>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgMenuIcon = itemView.ivProduct
        private val tvMenuTitle = itemView.tvMenuTitle

        fun bindItem(menuHome: MenuHome, listener: (MenuHome) -> Unit) {
            imgMenuIcon.setImageResource(menuHome.menuIcon)
            tvMenuTitle.text = menuHome.menuName
            itemView.setOnClickListener { listener(menuHome) }
        }
    }
}
