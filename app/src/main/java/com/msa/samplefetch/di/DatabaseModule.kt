package com.msa.samplefetch.di

import android.content.Context
import androidx.room.Room
import com.msa.data.db.Database
import com.msa.data.db.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "movie.db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun ProvideDao(database: Database): ProductDao {
        return database.getProductDao()
    }
}