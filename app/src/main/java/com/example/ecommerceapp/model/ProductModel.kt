package com.example.ecommerceapp.model

data class ProductModel(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val actualPrice: Double = 0.0,
    val category: String? = "",
    val categoryId: String? = "",
    val images: String = ""
)