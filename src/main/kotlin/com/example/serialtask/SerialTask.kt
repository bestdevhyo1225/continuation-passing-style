package com.example.serialtask

class SerialTask(private val dispatcher: Dispatcher, vararg blocks: (Controller) -> Unit) : Runnable {

    private var task: Task

    init {
        if (blocks.isEmpty()) {
            throw Throwable("blocks is empty")
        }

        var prev = Task(blocks.first())
        this.task = prev
        prev.isStarted = Status.MARK

        for (i in 1..blocks.lastIndex) {
            val next = Task(blocks[i])
            prev.next = next
            prev = next
        }
    }

    override fun run() {
        while (!Thread.currentThread().isInterrupted) {
            Thread.sleep(5)

            if (task.isCompleted == Status.MARK) {
                task.next?.let {
                    it.isStarted = Status.MARK
                    task = it
                }
            }

            if (task.isStarted == Status.MARK) {
                task.run(Controller(task))
                task.isStarted = Status.CONFIRM
            }
        }
    }

    fun launch() {
        dispatcher.start(serialTask = this)
    }
}
