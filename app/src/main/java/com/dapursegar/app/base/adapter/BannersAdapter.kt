package com.dapursegar.app.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.dapursegar.app.R
import com.dapursegar.app.base.extension.load

/**
 * Created by Ramana on 22-Sep-19.
 */

class BannersAdapter(private val state: String) : PagerAdapter() {

    companion object {
        const val PROMO = "PROMO"
        const val PRODUCT = "PRODUCT"
    }

    private var banners = mutableListOf<Int>()

    override fun getCount(): Int = banners.size

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = when (state) {
            PROMO -> {
                LayoutInflater.from(container.context)
                    .inflate(R.layout.default_banner, container, false)
            }
            PRODUCT -> {
                LayoutInflater.from(container.context)
                    .inflate(R.layout.banner_product, container, false)
            }
            else -> {
                throw IllegalArgumentException("No state detected")
            }
        }
        val ivSlider = view.findViewById<ImageView>(R.id.ivSlider)
        ivSlider.load(banners[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    private fun getImageAt(position: Int): String {
        return ""
    }

    fun setBanners(banners: MutableList<Int>) {
        this.banners = banners
        notifyDataSetChanged()
    }
}