package com.steliosf.unittestingdiet.fixtures.common

import com.steliosf.unittestingdiet.common.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class TestCoroutineDispatcherProvider : CoroutineDispatcherProvider {
    override val main: CoroutineDispatcher = TestCoroutineDispatcher()
}
