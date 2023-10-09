package com.steliosf.unittestingdiet.data.datasource

import com.steliosf.unittestingdiet.domain.model.Message
import com.steliosf.unittestingdiet.domain.model.User

interface ChatDataSource {
    suspend fun getChatMessages(user: User): List<Message>
    suspend fun sendChatMessage(message: Message, user: User)
}
