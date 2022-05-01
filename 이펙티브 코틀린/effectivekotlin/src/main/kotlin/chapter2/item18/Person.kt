package chapter2.item18

class Person(
    val id: Int =0,
    val name: String ="",
    val surname: String = ""
): Human(id,name) {

}

public fun<T> Iterable<T>.joinToString(
    separator: CharSequence = ", ",
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    limit: Int = -1,
    truncated: CharSequence ="...",
    transform: ((T) -> CharSequence)? = null
):String{
    return ""
}



// 이렇게 쓰면 안된다.
//
// class Person(val id: Int =0,
//    val name: String ="",
//    val surname: String = ""): Human(id,name) {
//
//}

