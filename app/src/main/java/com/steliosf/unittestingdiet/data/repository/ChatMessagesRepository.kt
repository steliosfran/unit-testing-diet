package com.steliosf.unittestingdiet.data.repository

import com.steliosf.unittestingdiet.data.datasource.ChatDataSource
import com.steliosf.unittestingdiet.domain.model.Message
import com.steliosf.unittestingdiet.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class ChatMessagesRepository(
    private val chatDataSource: ChatDataSource
) {
    private val _cachedChatMessages = MutableStateFlow<List<Message>>(emptyList())

    fun getChatMessages(user: User): Flow<List<Message>> = flow {
        if (_cachedChatMessages.value.isEmpty()) {
            _cachedChatMessages.emit(chatDataSource.getChatMessages(user))
        }
        emitAll(_cachedChatMessages)
    }

    suspend fun sendChatMessage(message: Message, user: User) {
        _cachedChatMessages.apply { value = value + message }
        chatDataSource.sendChatMessage(message, user)
    }
}
