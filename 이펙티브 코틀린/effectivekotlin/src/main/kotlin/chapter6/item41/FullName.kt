package chapter6.item41

import kotlinx.coroutines.runBlocking

data class FullName(
    var name: String,
    var surname: String
)

fun main() = runBlocking {
    val person = FullName("Charles", "com")
    val s = mutableSetOf<FullName>()
    s.add(person)
    println(person in s) // true
    person.surname = "Ok" // 여기서 person의 hashCode 값이 변경됨
    println(person) // FullName(name=Charles, surname=Ok)
    println(person in s) // false, 버킷의 재배치가 이루어 지지 않기 때문에 false 반환
    println(s.first() == person) // true
}