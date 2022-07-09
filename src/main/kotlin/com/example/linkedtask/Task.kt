package com.example.linkedtask

class Task(val run: (Controller) -> Unit) {
    var isCompleted: Boolean = false
    var result: Result<Any?>? = null
    var next: Task? = null
}
