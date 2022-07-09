package com.example.continuationtask

class Task internal constructor(internal val func: (Continuation) -> Unit){
    internal val continuation = Continuation(task = this)
    internal var isStarted = Status.READY
    internal var isCompleted = Status.READY
    internal var env: MutableMap<String, Any?> = hashMapOf()
}
