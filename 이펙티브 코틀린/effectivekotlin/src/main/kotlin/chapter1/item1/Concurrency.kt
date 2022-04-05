package chapter1.item1

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

private fun multiThread() {
    var num = 0
    for (i in 1..1000) {
        thread {
            Thread.sleep(10)
            num += 1
        }
    }
    Thread.sleep(5000)
    println(num) // 1000이 아닐 확률이 매우 높음
}

private suspend fun coroutine() {
    var num = 0
    coroutineScope {
        for (i in 1..1000) {
            launch {
                delay(10)
                num += 1
            }
        }
    }
    println(num)
}

private fun locking() {
    val lock = Any()
    var num = 0

    for (i in 1..1000) {
        thread {
            Thread.sleep(10)
            synchronized(lock) {
                num += 1
            }
        }
    }

    Thread.sleep(100)
    println(num)
}

suspend fun main() {
    multiThread()
    multiThread()
    coroutine()
    coroutine()
    locking()
}