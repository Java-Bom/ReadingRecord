package chapter1.item2

import chapter1.item1.User

val a = 1
fun fizz() {
    val b = 2
    println(a + b)
}

val buzz = {
    val c = 3
    println(a + c)
}

fun updateWeather(degrees: Int) {
    val description: String
    val color: String
    if (degrees < 5) {
        description = "cold"
        color = "BLUE"
    } else if (degrees < 23) {
        description = "mild"
        color = "YELLOW"
    } else {
        description = "hot"
        color = "RED"
    }
}

fun updateWeather2(degrees: Int) {
    val (description, color) = when {
        degrees < 5 -> "color" to "BLUE"
        degrees < 23 -> "mild" to "YELLOW"
        else -> "hot" to "RED"
    }
}

fun main() {
    var user: User
    val users = listOf<User>()
    for (i in users.indices) {
        user = users[i]
        println("User at $i is $user")
    }

    for (i in users.indices) {
        val user = users[i]
        println("User at $i is $user")
    }

    for ((i, user) in users.withIndex()){
        println("User at $i is $user")
    }
}
