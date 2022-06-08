package chapter6.item42

class User(val name: String, val surname: String) : Comparable<User> {
    //    override fun compareTo(other:User) : Int = compareValuesBy(this, other, {it.surname}, {it.name})
    override fun compareTo(other: User): Int = compareValues(surname, other.surname)
}

val names = listOf<User>()

val sorted = names.sortedBy { it.surname }

val sorted2 = names.sortedWith(compareBy({ it.surname }, { it.name }))