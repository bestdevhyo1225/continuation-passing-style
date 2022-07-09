package com.example.continuationtask

class Continuation internal constructor(private val task: Task) {

    var step = 0
        private set

    operator fun get(key: String): Any? = task.env[key]
    operator fun set(key: String, value: Any?) {
        task.env[key] = value
    }

    internal var failed: Throwable? = null

    fun cancel(throwable: Throwable) {
        failed = Throwable("step: $step, env: ${task.env}}", throwable)
        task.isCompleted = Status.MARK
    }

    fun complete() {
        task.isCompleted = Status.MARK
    }

    fun resume(step: Int) {
        this.step = step
        task.isStarted = Status.READY
    }
}
