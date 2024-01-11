package com.test.weatheapp.presentation.base

import androidx.lifecycle.ViewModel
import com.test.weatheapp.presentation.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext


//Base View model
abstract class BaseViewModel(private val contextProvider: CoroutineContextProvider) : ViewModel() {

    abstract val coroutineExceptionHandler: CoroutineExceptionHandler


    //Method for consume a coroutine with io context
    protected suspend fun <T> withIOContext(block: suspend CoroutineScope.() -> T): T {
        return withContext(contextProvider.io + coroutineExceptionHandler, block)
    }

    //Method for consume a coroutine with main context

    protected suspend fun <T> withMainContext(block: suspend CoroutineScope.() -> T): T {
        return withContext(contextProvider.main + coroutineExceptionHandler, block)
    }

    public override fun onCleared() {
        super.onCleared()
    }
}
