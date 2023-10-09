package com.steliosf.unittestingdiet.presentation.viewmodel

import com.steliosf.unittestingdiet.domain.model.User
import com.steliosf.unittestingdiet.presentation.ui.model.MessageUiModel
import com.steliosf.unittestingdiet.domain.usecase.GetChatMessagesUseCase
import com.steliosf.unittestingdiet.domain.usecase.SendTextMessageUseCase
import com.steliosf.unittestingdiet.presentation.ui.mapper.MessageUiModelMapper
import io.reactivex.rxjava3.core.Observable

class ChatViewModel(
    private val getChatMessagesUseCase: GetChatMessagesUseCase,
    private val sendTextMessageUseCase: SendTextMessageUseCase,
    private val user: User
) : BaseViewModel() {

    data class State(
        val messages: List<MessageUiModel>
    )

    val state: Observable<State>
        get() = getChatMessagesUseCase(user).map {
            State(MessageUiModelMapper.fromMessagesList(it))
        }

    fun onTextMessageSent(text: String) {
        sendTextMessageUseCase(text, user).safeSubscribe()
    }
}
