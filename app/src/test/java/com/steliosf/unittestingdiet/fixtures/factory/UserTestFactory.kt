package com.steliosf.unittestingdiet.fixtures.factory

import com.steliosf.unittestingdiet.domain.model.User
import java.util.UUID

class UserTestFactory(
    private val id: String = UUID.randomUUID().toString(),
    private val name: String = "test name",
) {

    fun produce(): User {
        return User(id = id, name = name)
    }
}
