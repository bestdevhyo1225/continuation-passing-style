package com.example.continuationtask

import java.util.concurrent.Executors

interface Dispatcher {

    fun start(continuationTask: ContinuationTask)
    fun join()

    class FixedDispatcher(threads: Int) : Dispatcher {

        private val executor = Executors.newFixedThreadPool(threads)

        override fun start(continuationTask: ContinuationTask) {
            executor.execute(continuationTask)
        }

        override fun join() {
            while (!executor.isShutdown) {
            }
        }
    }
}
