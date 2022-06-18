package chapter7.item49

interface Iterable<out T> {
    operator fun iterator(): Iterator<T>
}

interface Sequence<out T> {
    operator fun iterator(): Iterator<T>
}

fun main() {
    val seq = sequenceOf(1, 2, 3)
    val filtered = seq.filter { print("f$it"); it % 2 == 1 }
    println(filtered) // FilteringSequence@...

    val asList = filtered.toList()
    // f1 f2 f3
    println(asList) // [1 , 3]

    val list = listOf(1, 2, 3)
    val listFiltered =  list
        .filter { print("f$it"); it % 2 == 1 }
    // f1 f2 f3
    println(listFiltered) // [1 , 3]

    sequenceOf(1, 2, 3)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("F$it, "); it * 2 }
        .forEach { println("E$it, ") }
    // 출력 : F1, M1, E2, F2, F3, M3, E6,

    listOf(1, 2, 3)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2 }
        .forEach { print("E$it, ") }

    // 출력 : F1, F2, F3, M1, M3, E2, E6


    for (e in listOf(1, 2, 3)) {
        print("F$e, ")
        if (e % 2 == 1) {
            print("M$e, ")
        }
        val mapped = e * 2
        print("E$mapped, ")
    }

    (1..10).asSequence()
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2}
        .find { it > 5 }
    // 출력 : F1, M1, F2, F3, M3,

    (1..10)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2 }
        .filter { it > 5 }
    // 출력 : F1, F2, F3, F4, F5, F6, F7, F8, F9, F10
    // M1, M3, M5, M7, M9,

    val fibonacci = sequence {
        yield(1)
        var current = 1
        var prev = 1

        while (true) {
            yield(current)
            val temp  = prev
            prev = current
            current += temp
        }
    }

    println(fibonacci.take(10).toList())
    // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]

    generateSequence(0) { it + 1 }.take(10).sorted().toList()
    generateSequence(0) { it + 1 }.sorted().take(10).toList()
}
