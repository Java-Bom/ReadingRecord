package chapter6.item44

class PhoneBookIncorrect {
//    fun String.isPhoneNumber() = length == 7 && all {it.isDigit()}
}

fun String.isPhoneNumber() = length == 7 && all {it.isDigit()}

fun main() {
//    PhoneBookIncorrect().apply { "1234567890".isPhoneNumber() }
    B().print()
}

val ref = String::isPhoneNumber
val str = "123456789"
val boundedRef = str::isPhoneNumber

//val refX = PhoneBookIncorrect::isPhoneNumber // error
//val book = PhoneBookIncorrect()
//val boundedRefX = book::isPhoneNumber // error

class A{
    val a = 10
}
class B{
    val a = 20
    val b = 30

    fun A.test() = a + b

    fun print(){
        println(A().test())
    }
}



