package com.tani.app.model.home

data class CategoryHome(
    var categoryName: String = "",
    var products : MutableList<ProductItem> = mutableListOf()
)