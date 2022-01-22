package io.github.bonarmada.gw_challenge.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    open val disposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}