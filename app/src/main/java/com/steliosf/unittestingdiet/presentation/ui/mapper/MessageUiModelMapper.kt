package com.steliosf.unittestingdiet.presentation.ui.mapper

import com.steliosf.unittestingdiet.domain.model.Message
import com.steliosf.unittestingdiet.presentation.ui.model.MessageUiModel

object MessageUiModelMapper {
    fun fromMessagesList(messagesList: List<Message>): List<MessageUiModel> {
        return messagesList.map { MessageUiModel(text = it.text, type = it.type) }
    }
}
