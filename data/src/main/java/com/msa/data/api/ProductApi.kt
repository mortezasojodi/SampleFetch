package com.msa.data.api

import com.msa.data.model.ProductModel
import retrofit2.http.GET

interface ProductApi {
    @GET("/products")
    suspend fun GetAll() : List<ProductModel>
}