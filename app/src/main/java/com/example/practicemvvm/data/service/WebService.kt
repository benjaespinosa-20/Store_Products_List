package com.example.practicemvvm.data.service

import com.example.practicemvvm.data.model.ListProducts
import com.example.practicemvvm.data.model.Products
import com.example.practicemvvm.utils.Constans
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {
    @GET("products")
    suspend fun getListProduct(): ListProducts
}

object RetrofitClient{
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}