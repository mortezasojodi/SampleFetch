package com.msa.data.repository.Chat

import com.msa.data.api.ChatApi
import com.msa.domain.Command.PagerCommand
import com.msa.domain.entity.MessageEntity
import com.msa.domain.repository.ChatRepository

class ChatRepositoryImp constructor(val chatApi: ChatApi) : ChatRepository {
    override suspend fun GetAllPaginate(command: PagerCommand): Result<List<MessageEntity>> {
        return chatApi.GetAllPaginate(command)
    }
}