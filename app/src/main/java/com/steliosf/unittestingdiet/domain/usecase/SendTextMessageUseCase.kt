package com.steliosf.unittestingdiet.domain.usecase

import com.steliosf.unittestingdiet.data.repository.ChatMessagesRepository
import com.steliosf.unittestingdiet.domain.factory.MessageFactory
import com.steliosf.unittestingdiet.domain.model.User
import com.steliosf.unittestingdiet.domain.model.error.InvalidMessageException

class SendTextMessageUseCase(
    private val chatMessagesRepository: ChatMessagesRepository
) {

    suspend operator fun invoke(text: String, user: User) {
        if (isValid(text)) {
            val message = MessageFactory.fromText(text)
            chatMessagesRepository.sendChatMessage(message, user)
        } else {
            throw InvalidMessageException("Message is too long")
        }
    }

    private fun isValid(text: String): Boolean {
        return text.length < 180
    }
}
