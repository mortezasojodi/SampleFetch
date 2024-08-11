package com.msa.domain.usecase.product

import com.msa.domain.repository.ProductRepository

class GetAllProductUsecase(private val repository: ProductRepository) {
    suspend fun invoke() = repository.GetAll()
}