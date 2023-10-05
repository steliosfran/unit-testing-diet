package com.steliosf.unittestingdiet.di

import com.steliosf.unittestingdiet.data.repository.ChatMessagesRepository
import com.steliosf.unittestingdiet.domain.usecase.GetChatMessagesUseCase
import com.steliosf.unittestingdiet.domain.usecase.SendTextMessageUseCase
import com.steliosf.unittestingdiet.presentation.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::ChatViewModel)

    factoryOf(::GetChatMessagesUseCase)
    factoryOf(::SendTextMessageUseCase)

    singleOf(::ChatMessagesRepository)
}
