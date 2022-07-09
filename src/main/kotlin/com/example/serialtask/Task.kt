package com.example.serialtask

class Task internal constructor(internal val run: (Controller) -> Unit) {
    internal var isStarted = Status.READY
    internal var isCompleted = Status.READY
    internal var result: Result<Any?>? = null
    internal var next: Task? = null
}
