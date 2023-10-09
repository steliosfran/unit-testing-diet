package com.steliosf.unittestingdiet.domain.usecase

import com.steliosf.unittestingdiet.data.repository.ChatMessagesRepository
import com.steliosf.unittestingdiet.domain.model.Message
import com.steliosf.unittestingdiet.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetChatMessagesUseCase(
    private val chatMessagesRepository: ChatMessagesRepository
) {

    operator fun invoke(user: User): Flow<List<Message>> {
        return chatMessagesRepository.getChatMessages(user).map { messagesList ->
            messagesList.sortedBy { it.date.time }
        }
    }
}
