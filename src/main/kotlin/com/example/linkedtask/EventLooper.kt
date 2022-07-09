package com.example.linkedtask

import java.util.LinkedList
import java.util.Queue

class EventLooper(private val dispatcher: Dispatcher) : Runnable {

    private val tasks: Queue<Task> = LinkedList()
    private var currentTask: Task? = null

    override fun run() {
        // 실행되는 스레드가 인터럽트 되는 것을 대비해서 체크를 해야한다.
        while (!Thread.currentThread().isInterrupted) {
            Thread.sleep(16)
            synchronized(this) {
                if (currentTask != null) {
                    currentTask?.let { current ->
                        if (current.isCompleted) {
                            current.next?.let { next -> tasks.add(next) }
                            currentTask = null
                        }
                    }
                } else tasks.poll()?.let { current ->
                    currentTask = current
                    current.run(Controller(current))
                }
            }
        }
    }

    fun linkedTask(vararg blocks: (Controller) -> Unit) {
        if (blocks.isEmpty()) return
        synchronized(tasks) {
            var prev = Task(blocks.first())
            tasks.add(prev)

            // 루프가 끝나도 tasks Queue 에는 하나만 들어있다.
            for (i in 1..blocks.lastIndex) {
                val task = Task(blocks[i])
                // 현재 prev.next 의 task 를 지정한다.
                prev.next = task
                // 다음 인덱스의 task 설정을 위해서 현재 task 를 prev 로 변경한다.
                prev = task
            }
        }
    }

    fun launch() {
        dispatcher.start(eventLooper = this)
    }

    fun join() {
        dispatcher.join()
    }
}
