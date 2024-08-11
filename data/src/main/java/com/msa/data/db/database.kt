package com.msa.data.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities =[ProductLocalEntity::class],
    version = 1
)
abstract class Database : RoomDatabase() {

  abstract fun getProductDao() : ProductDao
}
