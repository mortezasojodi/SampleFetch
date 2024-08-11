package com.msa.domain.usecase.product.Chat

import com.msa.domain.Command.PagerCommand
import com.msa.domain.entity.MessageEntity
import com.msa.domain.repository.ChatRepository

class ChatGetAllPaginate constructor(val repository: ChatRepository) {
    suspend fun invoke(command: PagerCommand): Result<List<MessageEntity>> =
        repository.GetAllPaginate(command)
}