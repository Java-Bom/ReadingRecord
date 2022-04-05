package chapter1.item1

import kotlin.concurrent.thread
import kotlin.properties.Delegates

data class User(
    val name: String
)

class UserRepository {
    private val storedUsers: MutableMap<Int, String> = mutableMapOf()

    fun loadAll(): MutableMap<Int, String> {
        return storedUsers
    }
}

fun main() {
    val list1: MutableList<Int> = mutableListOf()
    var list2: List<Int> = listOf()

    list1.add(1)
    list2 = list2 + 1

    list1 += 1 // list1.plusAssign(1)
    list2 += 1 // list2 = list2.plus(1)


    var list = listOf<Int>()
    for(i in 1..1000) {
        thread {
            list = list + i
        }
    }
    Thread.sleep(1000)
    println(list.size)

    var names by Delegates.observable(listOf<String>()) {_, old, new ->
        println("Names changed from $old to $new")
    }

    names += "Fabio"
    names += "Bill"
}