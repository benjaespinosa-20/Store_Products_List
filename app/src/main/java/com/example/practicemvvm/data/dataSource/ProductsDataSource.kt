package com.example.practicemvvm.data.dataSource

import com.example.practicemvvm.data.model.ListProducts
import com.example.practicemvvm.data.service.WebService

class ProductsDataSource(private val webService: WebService) {
    suspend fun getProductList(): ListProducts = webService.getListProduct()
}