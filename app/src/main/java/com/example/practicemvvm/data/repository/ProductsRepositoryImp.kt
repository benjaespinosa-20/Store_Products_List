package com.example.practicemvvm.data.repository

import com.example.practicemvvm.data.dataSource.ProductsDataSource
import com.example.practicemvvm.data.model.ListProducts
import com.example.practicemvvm.data.service.WebService

class ProductsRepositoryImp(private val dataSource: ProductsDataSource): ProductsRepository {
    override suspend fun getProducts(): ListProducts = dataSource.getProductList()
}