package com.example.continuationtask

fun main(args: Array<String>) {
    val threads = 10
    val dispatcher = Dispatcher.FixedDispatcher(threads = threads)
    for (i in 0..5) {
        ContinuationTask(dispatcher = dispatcher, isLazy = false) { continuation ->
            when (continuation.step) {
                0 -> {
                    println("$i-0 ${Thread.currentThread().id}")
                    Thread.sleep(i * 100L)
                    continuation.resume(step = 1)
                }
                1 -> {
                    println("$i-1 ${Thread.currentThread().id}")
                    Thread.sleep(i * 100L)
                    continuation.resume(step = 2)
                }
                2 -> {
                    println("$i-2 ${Thread.currentThread().id}")
                    Thread.sleep(i * 100L)
                    continuation.complete()
                }
            }
        }
    }
    dispatcher.join()
}

