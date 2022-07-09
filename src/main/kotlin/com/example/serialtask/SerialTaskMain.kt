package com.example.serialtask

fun main(args: Array<String>) {
    val threads = 10
    val dispatcher = Dispatcher.FixedDispatcher(threads = threads)
    for (i in 0..5) {
        val serialTask = SerialTask(dispatcher = dispatcher, {
            println("$i-0 ${Thread.currentThread().id}")
            Thread.sleep(i * 100L)
            it.resume()
        }, {
            println("$i-1 ${Thread.currentThread().id}")
            Thread.sleep(i * 100L)
            it.resume()
        }, {
            println("$i-2 ${Thread.currentThread().id}")
            Thread.sleep(i * 100L)
            it.resume()
        })
        serialTask.launch()
    }
    dispatcher.join()
}
