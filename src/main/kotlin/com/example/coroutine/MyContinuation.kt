package com.example.coroutine

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class MyContinuation(
    override val context: CoroutineContext = EmptyCoroutineContext,
) : Continuation<Unit> {

    override fun resumeWith(result: Result<Unit>) {
        println("file read done!")
    }
}
