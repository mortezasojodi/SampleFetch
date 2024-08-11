package com.msa.domain.entity

data class MessageEntity(
    val Id: Int,
    val Text: String,
    val Date: Long, // time milis
    val SendByMe: Boolean

)