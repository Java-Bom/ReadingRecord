package chapter1.item1

import java.util.SortedSet
import java.util.TreeSet

inline fun<T, R> Iterable<T>.map(
    transformation: (T) -> R
): List<R> {
    val list = ArrayList<R>()
    for (elem in this){
        list.add(transformation(elem))
    }
    return list
}

inline fun downCasting() {
    val list = listOf(1, 2, 3)

    if (list is ArrayList) {
        list.add(4)
    }
    println(list)
}

private fun mutableHashTable() {
    val names: SortedSet<FullName> = TreeSet()
    val person = FullName("AAA", "AAA")
    names.add(person)
    names.add(FullName("Jordan", "Hansen"))
    names.add(FullName("David", "Blanc"))
    println(names)
    println(person in names) // true

    person.name = "ZZZ"
    println(names)
    println(person in names) // false
}

class FullName(
    var name: String,
    var surname: String,
): Comparable<FullName> {
    override fun compareTo(other: FullName): Int {
        return this.name.compareTo(other.name)
    }
}
fun main() {
    downCasting()
    mutableHashTable()
}
