package com.example.linkedtask

fun main(args: Array<String>) {
    val threads = 10
    val eventLooper = EventLooper(Dispatcher.FixedDispatcher(threads = threads))
    for (i in 0..5) {
        eventLooper.linkedTask(
            {
                println("$i-0 ${Thread.currentThread().id}")
                Thread.sleep(i * 100L)
                it.resume()
            },
            {
                println("$i-1 ${Thread.currentThread().id}")
                Thread.sleep(i * 100L)
                it.resume()
            },
            {
                println("$i-2 ${Thread.currentThread().id}")
                Thread.sleep(i * 100L)
                it.resume()
            },
        )
    }
    eventLooper.launch()
    eventLooper.join()
}
