package com.steliosf.unittestingdiet.presentation.ui.model

import com.steliosf.unittestingdiet.domain.model.Message

data class MessageUiModel(
    val text: String,
    val type: Message.Type
)
