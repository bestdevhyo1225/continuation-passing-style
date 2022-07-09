package com.example.continuationtask

class ContinuationTask(
    private val dispatcher: Dispatcher,
    isLazy: Boolean,
    func: (Continuation) -> Unit
) : Runnable {

    private val task = Task(func = func)

    init {
        if (!isLazy) launch()
    }

    override fun run() {
        while (!Thread.currentThread().isInterrupted) {
            Thread.sleep(5)
            if (task.isCompleted == Status.MARK) break
            if (task.isStarted == Status.READY) {
                task.isStarted = Status.CONFIRM
                task.func(task.continuation)
            }
        }
        task.continuation.failed?.let { throw it }
    }

    fun launch() {
        dispatcher.start(continuationTask = this)
    }
}
