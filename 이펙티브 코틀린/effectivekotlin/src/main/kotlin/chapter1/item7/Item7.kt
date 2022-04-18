package item7

import java.lang.IllegalStateException
import java.util.*

val random = Random()

fun someMethod(): Result<String> {
    val num = random.nextInt(5)
    return if (num == 1) {
        Failure(IllegalStateException())
    } else {
        Success("직업 성공")
    }
}


sealed class Result<out T>
class Success<out T>(val result: T) : Result<T>()
class Failure(val throwable: Throwable) : Result<Nothing>()