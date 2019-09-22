package com.tani.app.model.home


import com.google.gson.annotations.SerializedName
import com.tani.app.R

data class MenuHome(
    @SerializedName("menuIcon")
    var menuIcon: Int,
    @SerializedName("menuName")
    var menuName: String
) {
    companion object {
        fun getMenus(): MutableList<MenuHome> = mutableListOf<MenuHome>().apply {
            add(MenuHome(R.drawable.ic_fruit, "Fruit"))
            add(MenuHome(R.drawable.ic_vegetable, "Vegetable"))
            add(MenuHome(R.drawable.ic_riceandseeds, "Rice and seeds"))
            add(MenuHome(R.drawable.ic_poultrynegg, "Poultry and Egg"))
            add(MenuHome(R.drawable.ic_fish, "Fish"))
            add(MenuHome(R.drawable.ic_wholesale, "Wholesale"))
        }
    }
}