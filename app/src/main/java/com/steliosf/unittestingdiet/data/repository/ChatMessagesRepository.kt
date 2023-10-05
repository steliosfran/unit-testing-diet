package com.steliosf.unittestingdiet.data.repository

import com.steliosf.unittestingdiet.data.datasource.ChatDataSource
import com.steliosf.unittestingdiet.domain.model.Message
import com.steliosf.unittestingdiet.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ChatMessagesRepository(
    private val chatDataSource: ChatDataSource
) {

    private val cachedChatMessages: BehaviorSubject<List<Message>> = BehaviorSubject.create()

    fun getChatMessages(user: User): Observable<List<Message>> {
        return if (cachedChatMessages.hasValue()) cachedChatMessages else {
            loadExistingChatMessages(user).andThen(cachedChatMessages)
        }
    }

    fun sendChatMessage(message: Message, user: User): Completable {
        return chatDataSource.sendChatMessage(message, user)
            .doOnSubscribe {
                cachedChatMessages.onNext(getCurrentMessagesList() + message)
            }.doOnComplete { /* .. */ }
    }

    private fun loadExistingChatMessages(user: User): Completable {
        return chatDataSource.getChatMessages(user)
            .doOnSuccess {
                cachedChatMessages.onNext(it)
            }.ignoreElement()
    }

    private fun getCurrentMessagesList(): List<Message> {
        return cachedChatMessages.value ?: emptyList()
    }
}
