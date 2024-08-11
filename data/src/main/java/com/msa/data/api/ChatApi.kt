package com.msa.data.api

import com.msa.domain.Command.PagerCommand
import com.msa.domain.entity.MessageEntity
import java.util.Date
import kotlin.time.Duration.Companion.milliseconds

class ChatApi {
    suspend fun GetAllPaginate(command: PagerCommand): Result<List<MessageEntity>> {
        return try {
            val result = FakeDataSource().ListGenerator(command)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}


class FakeDataSource {
    suspend fun ListGenerator(command: PagerCommand): List<MessageEntity> {
        val lstMessage: ArrayList<MessageEntity> = arrayListOf()
        for (i in 1 until command.Size) {
            val message: MessageEntity =
                MessageEntity(
                    i + ((command.Page - 1) * command.Size),
                    (i + ((command.Page - 1) * command.Size)).toString(),
                    System.currentTimeMillis(),
                    i % 2 == 0
                )
            lstMessage.add(message)
        }
        return lstMessage.toList()
    }
}





