package com.msa.domain.usecase.product

import com.msa.domain.repository.ProductLocalRepository

class GetAllLocalProduct(private val repository: ProductLocalRepository) {
    suspend fun invoke() = repository.GetAll()
}