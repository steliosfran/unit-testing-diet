package com.steliosf.unittestingdiet.data.datasource

import com.steliosf.unittestingdiet.domain.model.Message
import com.steliosf.unittestingdiet.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ChatDataSource {
    fun getChatMessages(user: User): Single<List<Message>>
    fun sendChatMessage(message: Message, user: User): Completable
}
