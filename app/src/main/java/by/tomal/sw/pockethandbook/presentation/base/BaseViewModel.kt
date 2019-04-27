package by.tomal.sw.pockethandbook.presentation.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {
    private val lazyDisposable = lazy (LazyThreadSafetyMode.NONE) {
        CompositeDisposable()
    }

    protected val disposableBag by lazyDisposable

    override fun onCleared() {
        super.onCleared()
        if(lazyDisposable.isInitialized()) {
            disposableBag.dispose()
        }
    }
}