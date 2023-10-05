package com.steliosf.unittestingdiet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    private val disposable = CompositeDisposable()

    protected fun Completable.safeSubscribe() {
        disposable.add(this.subscribe())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
