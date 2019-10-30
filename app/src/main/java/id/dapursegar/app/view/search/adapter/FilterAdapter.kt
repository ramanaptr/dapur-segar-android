package id.dapursegar.app.view.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.dapursegar.app.R
import id.dapursegar.app.base.extension.gone
import kotlinx.android.synthetic.main.items_filter.view.*

class FilterAdapter(
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    private var items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.items_filter, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
        if (items.size > 0 && (items.size - 1) == position) holder.itemView.vLine.gone()
    }

    fun setData(menus: MutableList<String>) {
        this.items = menus
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvFilterName = itemView.tvFilterName

        fun bindItem(data: String, listener: (String) -> Unit) {
            tvFilterName.text = data
            tvFilterName.setOnClickListener { listener(data) }
        }
    }
}