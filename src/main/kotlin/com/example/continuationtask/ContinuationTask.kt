package com.example.continuationtask

class ContinuationTask(
    private val dispatcher: Dispatcher,
    isLazy: Boolean,
    block: (Continuation) -> Unit
) : Runnable {

    private val task = Task(block)

    init {
        if (!isLazy) launch()
    }

    override fun run() {
        while (!Thread.currentThread().isInterrupted) {
            Thread.sleep(5)
            if (task.isCompleted == Status.MARK) break
            if (task.isStarted == Status.READY) {
                task.isStarted = Status.CONFIRM
                task.run(task.continuation)
            }
        }
        task.continuation.failed?.let { throw it }
    }

    fun launch() {
        dispatcher.start(continuationTask = this)
    }
}
