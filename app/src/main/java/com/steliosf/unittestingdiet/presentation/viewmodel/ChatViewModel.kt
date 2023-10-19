package com.steliosf.unittestingdiet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steliosf.unittestingdiet.common.CoroutineDispatcherProvider
import com.steliosf.unittestingdiet.domain.model.User
import com.steliosf.unittestingdiet.domain.usecase.GetChatMessagesUseCase
import com.steliosf.unittestingdiet.domain.usecase.SendTextMessageUseCase
import com.steliosf.unittestingdiet.presentation.ui.mapper.MessageUiModelMapper
import com.steliosf.unittestingdiet.presentation.ui.model.MessageUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ChatViewModel(
    private val getChatMessagesUseCase: GetChatMessagesUseCase,
    private val sendTextMessageUseCase: SendTextMessageUseCase,
    private val user: User,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    data class State(
        val messages: List<MessageUiModel>
    )

    private val _state = MutableStateFlow(State(emptyList()))
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        viewModelScope.launch(coroutineDispatcherProvider.main) {
            getChatMessagesUseCase(user).map {
                State(MessageUiModelMapper.fromMessagesList(it))
            }.collect { newState ->
                _state.value = newState
            }
        }
    }

    fun onTextMessageSent(text: String) = viewModelScope.launch(
        coroutineDispatcherProvider.main
    ) {
        sendTextMessageUseCase(text, user)
    }
}
