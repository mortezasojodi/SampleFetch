package com.msa.domain.repository

import com.msa.domain.Command.PagerCommand
import com.msa.domain.entity.MessageEntity

interface ChatRepository {
    suspend fun GetAllPaginate(command: PagerCommand): Result<List<MessageEntity>>
}