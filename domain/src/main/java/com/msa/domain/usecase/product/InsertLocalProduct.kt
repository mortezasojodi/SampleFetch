package com.msa.domain.usecase.product

import com.msa.domain.entity.ProductEntity
import com.msa.domain.repository.ProductLocalRepository

class InsertLocalProductUsecase(private val repository: ProductLocalRepository) {
    suspend fun invoke(data: List<ProductEntity>) = repository.SaveProduct(data)
}