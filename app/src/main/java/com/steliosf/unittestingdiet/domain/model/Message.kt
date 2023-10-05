package com.steliosf.unittestingdiet.domain.model

import java.util.Date

data class Message(
    val id: String,
    val text: String,
    val date: Date,
    val type: Type
) {
    enum class Type {
        Received,
        Pending,
        Sent
    }
}
