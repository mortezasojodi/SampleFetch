package com.msa.data.repository

import com.msa.data.db.ProductDao
import com.msa.data.db.toEntity
import com.msa.data.db.toLocal
import com.msa.domain.entity.ProductEntity
import com.msa.domain.repository.ProductLocalRepository
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class ProductLocalRepositoryImp constructor(
    private val executor: DiskExecutor,
    private val localApi: ProductDao

) : ProductLocalRepository {

    override suspend fun GetAll(): Result<List<ProductEntity>> =
        withContext(executor.asCoroutineDispatcher()) {
            try {
                val result = localApi.getAll()
                Result.success(result.map {
                    it.toEntity()
                })
            } catch (e: Exception) {
                Result.failure(e);

            }
        }

    override suspend fun SaveProduct(data: List<ProductEntity>) =
        withContext(executor.asCoroutineDispatcher()) {
            localApi.saveProduct(data.map { it.toLocal() })
        }

}


class DiskExecutor : Executor {

    private val executor: Executor = Executors.newSingleThreadExecutor()

    override fun execute(runnable: Runnable) {
        executor.execute(runnable)
    }
}
