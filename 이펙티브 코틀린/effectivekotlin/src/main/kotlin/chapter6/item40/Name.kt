package chapter6.item40

import kotlinx.coroutines.runBlocking
import java.util.*

data class Player(
    val id:Int,
    val name: String,
    val points: Int
)

class Name(val name: String)

fun main() = runBlocking {
    val player1 = Player(0,"Charles", 9999)
    val player2 = Player(0,"Charles", 9999)

    println(player1.equals(player2)) // true
    println(player1 == player2) // true
    println(player1 === player2) // false

    val name1 = Name("Javabom")
    val name2 = Name("Javabom")
    val name1Ref = name1

    name1 == name1 // true
    name1 == name2 // false
    name1 == name1Ref // true

}

data class DateTime(
    private var millis: Long = 0L,
    private var timeZone : TimeZone? = null
){
    private var asStringCache= ""
    private var changed = false
}



