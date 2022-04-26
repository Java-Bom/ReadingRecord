val KEY: String = "javabom"

fun keyIsCorrect(key: String) : Boolean = KEY == key

fun verifyKey(key : String) : Unit? = if (KEY == (key)) Unit else null

fun main() {
    val key = "hello"

    verifyKey(key) ?: return
    if(!keyIsCorrect(key)) return
}
