package com.example.continuationtask

fun main(args: Array<String>) {
    val threads = 10
    val dispatcher = Dispatcher.FixedDispatcher(threads = threads)
    for (i in 0..5) {
        ContinuationTask(dispatcher = dispatcher, isLazy = false) {
            when (it.step) {
                0 -> {
                    println("$i-0 ${Thread.currentThread().id}")
                    it.resume(step = 1)
                }
                1 -> {
                    println("$i-1 ${Thread.currentThread().id}")
                    it.resume(step = 2)
                }
                2 -> {
                    println("$i-2 ${Thread.currentThread().id}")
                    it.complete()
                }
            }
        }
    }
    dispatcher.join()
}

