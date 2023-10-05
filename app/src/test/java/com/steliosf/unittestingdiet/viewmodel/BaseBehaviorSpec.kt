package com.steliosf.unittestingdiet.viewmodel

import com.steliosf.unittestingdiet.di.appModule
import com.steliosf.unittestingdiet.fixtures.di.fakeChatDataSourceModule
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.BehaviorSpec
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.test.KoinTest

open class BaseBehaviorSpec : BehaviorSpec(), KoinTest {

    private val modulesToLoad: List<Module> = appModule + fakeChatDataSourceModule

    override fun isolationMode() = IsolationMode.InstancePerLeaf

    override suspend fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        startKoin {
            modules(modulesToLoad)
        }
    }

    override suspend fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        stopKoin()
    }
}
