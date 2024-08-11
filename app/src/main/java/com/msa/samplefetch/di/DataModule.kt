package com.msa.samplefetch.di

import com.msa.data.api.ChatApi
import com.msa.data.api.ProductApi
import com.msa.data.db.ProductDao
import com.msa.data.repository.Chat.ChatRepositoryImp
import com.msa.data.repository.DiskExecutor
import com.msa.data.repository.ProductLocalRepositoryImp
import com.msa.data.repository.ProductRepositoryImp
import com.msa.domain.repository.ChatRepository
import com.msa.domain.repository.ProductLocalRepository
import com.msa.domain.repository.ProductRepository
import com.msa.domain.usecase.product.Chat.ChatGetAllPaginate
import com.msa.domain.usecase.product.GetAllLocalProduct

import com.msa.domain.usecase.product.GetAllProductUsecase
import com.msa.domain.usecase.product.InsertLocalProductUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideChatRepository(): ChatRepository {
        return ChatRepositoryImp(ChatApi())
    }

    @Provides
    fun provideChatUseCase(repository: ChatRepository): ChatGetAllPaginate {
        return ChatGetAllPaginate(repository)
    }


    @Provides
    fun provideRepository(productApi: ProductApi): ProductRepository {
        return ProductRepositoryImp(productApi)
    }

    @Provides
    fun provideUsecase(repository: ProductRepository): GetAllProductUsecase {
        return GetAllProductUsecase(repository)
    }

    @Provides
    fun provideGetLocalUsecase(repository: ProductLocalRepository): GetAllLocalProduct {
        return GetAllLocalProduct(repository)
    }

    @Provides
    fun provideInsertocalUsecase(repository: ProductLocalRepository): InsertLocalProductUsecase {
        return InsertLocalProductUsecase(repository)
    }

    @Provides
    fun provideLocalRepository(
        productApi: ProductDao,
        executor: DiskExecutor
    ): ProductLocalRepository {
        return ProductLocalRepositoryImp(executor, productApi)
    }

}