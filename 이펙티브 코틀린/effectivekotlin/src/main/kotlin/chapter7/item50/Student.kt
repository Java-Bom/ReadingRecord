package chapter7.item50

class Student(val name: String?)


fun List<Student>.getNames(): List<String> = this
    .map { it.name }
    .filter { it != null }
    .map { it!! }

// better
//fun List<Student>.getNames(): List<String> = this
//    .map { it.name }
//    .filterNotNull()

// best
//fun List<Student>.getNames(): List<String> = this
//    .mapNotNull { it.name }


fun main() {
    val list = mutableListOf(1)
    list.map { it + 1 }
}