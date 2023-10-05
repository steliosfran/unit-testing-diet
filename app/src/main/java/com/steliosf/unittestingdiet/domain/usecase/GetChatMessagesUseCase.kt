package com.steliosf.unittestingdiet.domain.usecase

import com.steliosf.unittestingdiet.data.repository.ChatMessagesRepository
import com.steliosf.unittestingdiet.domain.model.Message
import com.steliosf.unittestingdiet.domain.model.User
import io.reactivex.rxjava3.core.Observable

class GetChatMessagesUseCase(
    private val chatMessagesRepository: ChatMessagesRepository
) {

    operator fun invoke(user: User): Observable<List<Message>> {
        return chatMessagesRepository.getChatMessages(user).map { messagesList ->
            messagesList.sortedBy { it.date.time }
        }
    }
}
