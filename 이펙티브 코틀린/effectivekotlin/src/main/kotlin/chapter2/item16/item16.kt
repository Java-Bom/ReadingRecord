package chapter2.item16

import java.util.*

class KotlinProperty{
    // 코틀린의 프로퍼티의 backing field
    var name: String? =null
        get() = field?.toUpperCase()
        set(value) {
            if(!value.isNullOrBlank()){
                field = value
            }
        }

    // 읽기 전용 프로퍼티는 field가 만들어지지 않는다.
    val fullName: String
       get() = "$name "

    // 코틀린의 프로퍼티 : 캡슐화 (Date 예제)
    var date: Date
        get() = Date(millis)
        set(value) {
            millis = value.time
        }

    var millis : Long = 0
}

// 코틀린은 인터페이스에도 프로퍼티 정의 가능 : 상속시 오버라이드도 가능
interface Person{
    val name: String
}

open class Supercomputer{
    open val theAnswer:Long = 42
}

class AppleComputer : Supercomputer(){
    override val theAnswer: Long = 1_800_275_2273
}

