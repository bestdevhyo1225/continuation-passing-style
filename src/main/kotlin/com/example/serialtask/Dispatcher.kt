package com.example.serialtask

import java.util.concurrent.Executors

interface Dispatcher {

    fun start(serialTask: SerialTask)
    fun join()

    class FixedDispatcher(threads: Int) : Dispatcher {

        private val executor = Executors.newFixedThreadPool(threads)

        override fun start(serialTask: SerialTask) {
            executor.execute(serialTask)
        }

        override fun join() {
            while (!executor.isShutdown) {
            }
        }
    }
}
