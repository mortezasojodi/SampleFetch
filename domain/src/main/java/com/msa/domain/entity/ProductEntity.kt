package com.msa.domain.entity

import kotlinx.serialization.Serializable

 data class ProductEntity(
     val id:Int,
     val title:String,
     val price:Float,
     val category:String,
     val description:String,
     val image:String,
     )

