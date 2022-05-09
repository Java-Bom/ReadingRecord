package chapter3.item21

import java.math.BigDecimal
import kotlin.reflect.KProperty

class LoggingProperty<T>(var value: T) {
    operator fun getValue(
            thisRef: Any?,
            prop: KProperty<*>
    ): T {
        println("${prop.name} returned vale $value")
        return value
    }

    operator fun setValue(
            thisRef: Any?,
            prop: KProperty<*>,
            newValue: T
    ) {
        val name = prop.name
        println("$name changed from $value to $newValue")
        value = newValue
    }

}

fun main() {
    var tokenaaa: String? by LoggingProperty(null)
    var attempts: Int by LoggingProperty(0)

    tokenaaa
    tokenaaa = "a"

    attempts
    attempts = 1
}