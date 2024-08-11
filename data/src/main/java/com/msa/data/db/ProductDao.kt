package com.msa.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("Select * From productEntity")
    fun getAll() : List<ProductLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProduct(productEntity: List<ProductLocalEntity>)

}