package com.example.linkedtask

import java.util.concurrent.Executors

interface Dispatcher {

    fun start(eventLooper: EventLooper)
    fun join()

    class FixedDispatcher(private val threads: Int) : Dispatcher {

        private val executor = Executors.newFixedThreadPool(threads)

        override fun start(eventLooper: EventLooper) {
            for (i in 1..threads) {
                executor.execute(eventLooper)
            }
        }

        override fun join() {
            while (!executor.isShutdown) {
            }
        }
    }
}
