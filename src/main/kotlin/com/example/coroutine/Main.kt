package com.example.coroutine

import kotlin.coroutines.startCoroutine

suspend fun main(args: Array<String>) {
    ::readFile.startCoroutine(completion = MyContinuation())
    callA()
    callB()
}

suspend fun readFile() {
    println("file read...")
}

suspend fun callA() {
    println("a call")
}

suspend fun callB() {
    println("b call")
}
