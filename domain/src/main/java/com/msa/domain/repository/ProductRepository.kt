package com.msa.domain.repository

import com.msa.domain.entity.ProductEntity

interface ProductRepository {
     suspend fun GetAll() : Result<List<ProductEntity>>
}