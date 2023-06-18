package com.example.practicemvvm.data.model

data class Products(
    val id: Int = -1,
    val title: String = "",
    val description: String = "",
    val price: Int = -1,
    val thumbnail: String = ""
)

data class ListProducts(
    val products: List<Products> = listOf()
)
