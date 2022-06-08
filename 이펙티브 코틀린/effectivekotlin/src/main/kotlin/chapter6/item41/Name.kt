import kotlinx.coroutines.runBlocking

class Name(
    var name: String,
    var surname: String
) {
    override fun equals(other: Any?): Boolean =
        other is Name
            && other.name == name
            && other.surname == surname

}

fun main() = runBlocking {
    val s  = mutableSetOf<Name>()
    s.add(Name("JavaBom", "Study"))
    val p = Name("JavaBom", "Study")
    print(p in s) // false
    print(p == s.first()) // true
}