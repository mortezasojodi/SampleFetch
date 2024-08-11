package com.msa.domain.repository

import com.msa.domain.entity.ProductEntity

interface ProductLocalRepository {

    suspend fun GetAll(): Result<List<ProductEntity>>
    suspend fun SaveProduct(data: List<ProductEntity>)

}