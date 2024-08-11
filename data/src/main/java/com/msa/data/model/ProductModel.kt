package com.msa.data.model

import com.google.gson.annotations.SerializedName
import com.msa.domain.entity.ProductEntity

data class ProductModel (
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("price") val price:Float,
    @SerializedName("category") val category:String,
    @SerializedName("description") val description:String,
    @SerializedName("image") val image:String,
)

fun ProductModel.toEntity()= ProductEntity(
    id = id,
    title = title,
    price = price,
    category = category,
    description = description,
    image = image,
    )