package chapter1.item1

val name: String? = "Marton"
val surname: String = "Braun"

val fullName: String?
    get() = name?.let { "$it $surname" }
val fullName2: String? = name?.let { "$it $surname" }

fun main() {
    if (fullName != null) {
//        println(fullName.length) // 오류
    }

    if (fullName2 != null) {
        println(fullName2.length)
    }
}