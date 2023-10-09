package com.steliosf.unittestingdiet.fixtures.datasource

import com.steliosf.unittestingdiet.data.datasource.ChatDataSource
import com.steliosf.unittestingdiet.domain.model.Message
import com.steliosf.unittestingdiet.domain.model.User

class FakeChatDataSource : ChatDataSource {
    private var messagesList = mutableListOf<Message>()

    override suspend fun getChatMessages(user: User): List<Message> {
        return messagesList
    }

    override suspend fun sendChatMessage(message: Message, user: User) {
        messagesList.add(message)
    }

    fun setResponse(messagesList: List<Message>) {
        this.messagesList = messagesList.toMutableList()
    }
}
