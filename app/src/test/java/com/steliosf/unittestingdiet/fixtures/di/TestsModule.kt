package com.steliosf.unittestingdiet.fixtures.di

import com.steliosf.unittestingdiet.common.CoroutineDispatcherProvider
import com.steliosf.unittestingdiet.data.datasource.ChatDataSource
import com.steliosf.unittestingdiet.fixtures.common.TestCoroutineDispatcherProvider
import com.steliosf.unittestingdiet.fixtures.datasource.FakeChatDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val testsModule = module {
    singleOf(::FakeChatDataSource) bind ChatDataSource::class
    factoryOf<CoroutineDispatcherProvider>(::TestCoroutineDispatcherProvider)
}
