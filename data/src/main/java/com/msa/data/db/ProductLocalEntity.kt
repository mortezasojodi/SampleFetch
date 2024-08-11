package com.msa.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.msa.domain.entity.ProductEntity

@Entity(tableName = "productEntity")
data class ProductLocalEntity (
    @PrimaryKey
    val id:Int,
    val title:String,
    val price:Float,
    val category:String,
    val description:String,
    val image:String,
)

fun ProductLocalEntity.toEntity() = ProductEntity(
    id = id,
    title = title,
    price = price,
    category = category,
    description = description,
    image = image,
)

fun ProductEntity.toLocal() = ProductLocalEntity(
    id = id,
    title = title,
    price = price,
    category = category,
    description = description,
    image = image,
    )