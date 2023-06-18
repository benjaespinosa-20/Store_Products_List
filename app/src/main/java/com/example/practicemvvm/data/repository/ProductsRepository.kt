package com.example.practicemvvm.data.repository

import com.example.practicemvvm.data.model.ListProducts

interface ProductsRepository {
    suspend fun getProducts(): ListProducts
}