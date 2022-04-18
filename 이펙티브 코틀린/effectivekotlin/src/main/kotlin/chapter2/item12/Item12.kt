package item12

fun Int.factorial(): Int = (1..this).product()
fun Iterable<Int>.product(): Int = fold(1) { acc, i -> acc * i }

infix fun Int.timesRepeated(operation: ()->Unit)={
    repeat(this) { operation() }
}