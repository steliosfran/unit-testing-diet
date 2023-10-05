package com.steliosf.unittestingdiet.domain.usecase

import com.steliosf.unittestingdiet.domain.model.User
import com.steliosf.unittestingdiet.domain.model.error.InvalidMessageException
import com.steliosf.unittestingdiet.data.repository.ChatMessagesRepository
import com.steliosf.unittestingdiet.domain.factory.MessageFactory
import io.reactivex.rxjava3.core.Completable

class SendTextMessageUseCase(
    private val chatMessagesRepository: ChatMessagesRepository
) {

    operator fun invoke(text: String, user: User): Completable {
        return if (isValid(text)) {
            val message = MessageFactory.fromText(text)
            chatMessagesRepository.sendChatMessage(message, user)
        } else {
            Completable.error(InvalidMessageException("Message is too long"))
        }
    }

    private fun isValid(text: String): Boolean {
        return text.length < 180
    }
}
