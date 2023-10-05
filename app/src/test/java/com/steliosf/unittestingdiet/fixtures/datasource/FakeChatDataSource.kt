package com.steliosf.unittestingdiet.fixtures.datasource

import com.steliosf.unittestingdiet.data.datasource.ChatDataSource
import com.steliosf.unittestingdiet.domain.model.Message
import com.steliosf.unittestingdiet.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class FakeChatDataSource : ChatDataSource {
    private var messagesList = mutableListOf<Message>()

    override fun getChatMessages(user: User): Single<List<Message>> {
        return Single.just(messagesList)
    }

    override fun sendChatMessage(message: Message, user: User): Completable {
        return Completable.fromCallable {
            messagesList.add(message)
        }
    }

    fun setResponse(messagesList: List<Message>) {
        this.messagesList = messagesList.toMutableList()
    }
}
