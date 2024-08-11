package com.msa.data.repository

import com.msa.data.api.ProductApi
import com.msa.data.model.toEntity
import com.msa.domain.entity.ProductEntity
import com.msa.domain.repository.ProductRepository

class ProductRepositoryImp constructor(
    private val api : ProductApi
) : ProductRepository {
    override suspend fun GetAll(): Result<List<ProductEntity>> = try {
        val result= api.GetAll()
        Result.success(result.map { it.toEntity() })
    } catch (e:Exception){
        Result.failure(e);
    }
}