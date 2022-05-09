package chapter3.item24

interface Dog
interface Cutie
data class Puppy(val name: String) : Dog, Cutie
data class Hound(val name: String) : Dog
data class Cat(val name: String) : Cutie


fun main() {
    val dogs = mutableListOf<Dog>(Hound("pluto"))

    fillWithPuppies(dogs)
    println(dogs)

    val animals = mutableListOf<Cutie>(Cat("felix"))
    fillWithPuppies(animals)

    println(animals)

}

fun fillWithPuppies(list: MutableList<in Puppy>) {
    list.add(Puppy("jim"))
    list.add(Puppy("amy"))
}