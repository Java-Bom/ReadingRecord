# Chapter 4 추상화 설계 정리

## Item 26. 함수 내부의 추상화 레벨을 통일하라

**컴퓨터 과학에서의 추상화**

- 복잡한 자료, 모듈, 시스템 등으로부터 핵심적인 개념 또는 기능을 간추려 내는 것

**추상화가 필요한 이유**

- 복잡성을 숨기고 사용되는 단순한 형식만 노출해 코드를 체계화하고 만드는 사람에게 변화의 자유를 주기 위해 추상화를 사용
- 자동차의 경우 내부적으로는 매우 복잡한 요소들이 많지만 추상화된 **핸들과 변속기 가속페달**만으로 자동차를 운전할 수 있음 → 굉장히 추상화가 잘된 예시

**추상화 레벨**

- 높은 레벨로 갈수록 물리 장치에서 멀어지고 프로그래밍에서는 일반적으로 높을 레벨일수록 프로세스와 멀어지고 관리해야하는 세부적인 내용이 적음
    - ex) C언어는 직접 메모리를 제어할 수 있지만 비교적 JVM 언어에 비해 복잡도가 높음

**추상화 레벨 통일**

- 코드도 추상화를 계층처럼 만들어서 사용할 수 있는데, 이를 위한 기본적인 도구가 바로 함수
- 함수도 높은 레벨과 낮은 레벨을 구분해서 사용해야 한다는 원칙이 있는데 이를 추상화 레벨 통일 (Single Level of Abstraction, SLA) 원칙 이라고 부름

**CoffeeMachine 예시**

**Before**

```kotlin
class CoffeeMachine {

    fun makeCoffee() {
        // 수백 개의 변수 선언
        // 복잡한 로직 처리
        // 낮은 수준의 최적화도 여기서 진행
    }
}
```

- 하나의 메소드에 수많은 로직을 다 넣는 경우

**After**

```kotlin
class CoffeeMachine {

    fun makeCoffee() {
      boilWater()
      brewCoffee()
      pourCoffee()
      pourMilk()  
    }

    private fun boilWater() {
        //
    }
    
    private fun brewCoffee() {
        //
    }
    
    private fun pourCoffee() {
        //
    }
    
    private fun pourMilk() {}
    //
}
```

- 위 코드에 비해 `makeCoffee` 동작하는 흐름을 빠르게 이해할 수 있음
- 낮은 레벨을 이해해야하는 경우 해당 부분의 코드만 보면됨
- 즉 함수는 작아야하고 최소한의 책임을 가져야만함

### 프로그램 아키텍처의 추상 레벨

- 추상화 계층이라는 개념은 함수보다 높은 레벨에서도 적용할 수 있음
    - 높은 레벨 문제 중심
    - 낮은 레벨 문제 중심
    - 낮은 레벨 구현 구조
    - 프로그래밍 언어 구조와 도구
    - 운영 체제 연산과 머신 명령
- 추상화를 구분하는 이유는 서브 시스템의 세부 사항을 숨김으로써 상호 운영성과 플랫폼 독립성을 얻기 위함이기 때문에 함수 뿐만이 아니라 모듈 시스템 설계할 때도 중요
    - ex) 입출력 모듈은 비즈니스 로직을 나타내는 모듈보다 낮은 레벨의 모듈

### **정리**

- 추상화 계층을 만듬으로써 서브 시스템의 세부 사항을 숨겨 상호 운영성과 플랫폼 독립성을 얻게 됨
- 함수, 클래스, 모듈 등의 다양항 방식을 통해 추상화를 분리
- 이때 각각의 레이어가 커지는 것은 좋지 않고 작고 최소한의 책임만 갖도록 하는 것이 좋음

## Item 27. 변화로부터 코드를 보호하려면 추상화를 사용하라

- 추상화를 잘하면 세부 사항을 잘 알지 못하더라도 변경에 자유로워짐

### 상수

- 리터럴을 상수 프로퍼티로 변경하게 되면 해당 값을 이름을 붙일 수 있고 값을 변경할때에 훨씬 쉽고 안전하게 변경이 가능하게됨

**Before**

```kotlin
fun isPasswordValid(text: String): Boolean {
    if (text.length < 7) return false
}
```

**After**

```kotlin
const val MIN_PASSWORD_LENGTH = 7

fun isPasswordValid(text: String): Boolean {
    if (text.length < MIN_PASSWORD_LENGTH) return false
}
```

- 이런식으로 상수로 빼면 장점
    - 의미있는 이름 부여
    - 값 변경에 안전함

### 함수

- 사용자에게 토스트 메시지를 자주 출력해야하는 상황이 발생하는 경우 아래와 같이 간단한 확장 함수를 만들어 사용할 수 있음

**Before**

```kotlin
fun Context.toast(
    message: String,
    duration: Int = Toast.LENGTH_LONG
) {
    Toast.makeText(this, message, duration).show()
}
```

- 여기서 스낵바라는 다른 형태의 방식으로 출력해야하는 요구사항 발생

```kotlin
fun Context.snackbar(
    message: String,
    duration: Int = Snackbar.LENGTH_LONG
) {
   //
}
```

- 위와 같이 스낵바를 출력하는 확장함수를 만들고 기존의 `Context.toast`를 `Context.snackbar`로 변경
- 그러나 위와 같으 방법은 다른 모듈이 해당 함수를 의존하고 있을때 영향을 줄 수 있기 때문에 좋지 않음

**After**

```kotlin
fun Context.showMessage(
    message: String,
    duration: MessageLength = MessageLength.LONG,
) {
    val toastDuration = when (duration) {
        MessageLength.SHORT -> Length.LENGTH_LONG
        MessageLength.LONG -> Length.LENGTH_LONG
    }
    
    Toast.makeText(this, message, toastDuration).show()
}
```

- 위 요구사항의 경우 메시지 출력하고 싶다는 의도 자체가 중요하기 duration을 enum으로 관리 및 입력을 받고 함수명을 메시지 출력만 하는 역할로써 높은 레벨의 함수로 옮김

### 클래스

- 클래스는 상태를 가질 수 있고 많은 함수를 가질 수 있기 때문에 함수보다 더욱 다양한 기능의 추상화 도구로 사용할 수 있음

```kotlin
class MessageDisplay(
    val context: Context
) {
    
    fun show(
        message: String,
        duration: MessageLength = MessageLength.LONG
    )  {
        val toastDuration = when (duration) {
            MessageLength.SHORT -> Length.LENGTH_LONG
            MessageLength.LONG -> Length.LENGTH_LONG
        }

        Toast.makeText(this.context, message, toastDuration).show()
    }
}

fun main() {
    val messageDisplay = MessageDisplay(Context())
    messageDisplay.show("message")
}
```

- 의존성 주입 프레임 워크를 사용해 클래스 생성 위임할 수 있음

```kotlin
@Inject lateinit var messageDisplay: MessageDisplay
```

- mock 객체를 활용해 해당 클래스에 의존하는 다른 클래스의 기능을 테스트 할 수 있음

```kotlin
val messageDisplay: MessageDisplay = mockk()
messageDisplay.setChristmasMode(true)
```

- 하지만 더 많은 확장성을 얻기 위해서는 인터페이스를 활용하는 것이 좋음

### 인터페이스

- 라이브러리의 경우 내부 클래스의 가시성을 제한하고, 인터페이스를 통해 노출하는 코드를 많이 사용
- 이렇게 하면 사용자가 클래스를 직접 사용하지 못해 라이브러리를 만드는 사람은 인터페이스만 유지하면 되기 때문에 사용자 측과의 결합을 줄일 수 있음
- 참고로 코틀린은 멀티 플랫폼 언어라 환경에 따라 다른 리스트를 리턴 및 플랫폼에 최적화를 시키고 있음

```kotlin
interface MessageDisplay {
    fun show(
        message: String,
        duration: MessageLength = MessageLength.LONG
    )
}

class ToastDisplay(
    val context: Context,
): MessageDisplay {
    override fun show(message: String, duration: MessageLength) {
        val toastDuration = when (duration) {
            MessageLength.SHORT -> Length.LENGTH_LONG
            MessageLength.LONG -> Length.LENGTH_LONG
        }

        Toast.makeText(this.context, message, toastDuration).show()
    }
}
```

- 인터페이스로 구성하면 해당 인터페이스의 의도만 제공하고 다양한 플랫폼에서 의도에 맞는 다양한 기능 직접 구현해 사용할 수 있음
- 추가로 테스트시에 인터페이스 faking이 클래스 mocking보다 간단하기 때문에 별도의 mocking 라이브러리를 사용하지 않아도됨
- 추가로 선언과 사용이 분리되어 있기 때문에 ToastDisplay 등의 실제 클래스를 자유롭게 변경할 수 있음

### 추상화가 주는 자유

- 아래와 같이 추상화하는 방법을 통해 추상화를 진행하게 되면 변화에 대한 자유를 제공함
    - 상수로 추출
    - 동작을 함수로 래핑
    - 함수를 클래스로 래핑
    - 인터페이스 뒤에 클래스를 숨김
    - 보편적인 객체를 특수한 객체로 래핑

### 추상화의 문제

- 모든 것을 추상화하게 되면 불필요하게 너무 많은 것을 숨기게 되고 간단한 의도나 동작을 이해하기 더욱 어려워질 수 있음
- 그래서 팀의 크기나 도메인 복잡도 등을 고려해 적절한 추상화를 진행해야함
- 적절한 균형을 찾는 것은 거의 예술에 가까운 것이기 때문에 많은 경험이 필요한 영역

### 정리

- 추상화는 단순하게 중복성을 제거해서 코드를 구성하기 위한 것이 아니고 코드를 변경해야할때 자유를 주는 것
- 하지만 너무 과한 추상화는 오히려 이해하기 힘든 구조로 변질될 수 있음

## Item 28. API 안정성을 확보하라

프로그래밍에서는 안정적이고 표준 API를 선호하는데 그 이유는 아래와 같음

- API가 변경되고 개발자가 이를 업데이트 했다면 여러 코드를 수동으로 업데이트 해야함
    - 많은 곳에서 api에 의존적이면 변경사항이 많을 수 있음
- 사용자가 새로운 API를 배워야함
    - 변경된 api를 쓰는 쪽에서는 변경사실을 알아야 하고 변경 부분에 대한 이해가 필요함

한번에 안정적인 API가 나왔으면 하지만 좋은 API 설계는 어렵기 때문에 우선 만들고 지속적으로 발전시켜 나가야 한다. 그래서 API 안정성을 지정해서 정보를 제공함으로써 api를 사용하는 곳에서 안정성을 확인할 수 있음

- 안정성 제공에 가장 간단한 방법은 문서에서 API 일부가 불안정한지 명확하게 지정
- 버전을 사용해 전체 라이브러리 또는 모듈의 안정성을 지정
    - Semantic Versioning : MAJOR.MINOR.PATCH의 3개 부분으로 버전 번홀르 구성
        - 호환되지 않는 API 변경을 수행하는 경우 MAJOR 버전
        - 이전 버전과 호환되는 방식으로 기능을 추가하는 경우 MINOR 버전
        - 이전 버전과 호환되는 버그를 수정을 할 때 PATCH 버전
- 추가로 안정적인 API에 새로운 요소를 추가할 때, 아직 해당 요소가 안정적이지 않다면 먼저 다른 브랜치에 해당 요소를 두고 일부 사용자가 이를 사용하다록 허용하려면 일단 `Experimental` 메타 어노테이션을 사용해 알려주는 것이 좋음

```kotlin
@Experimental(level = Experimental.Level.WARNING)
annotaion class ExperimentalNewApi

@ExperimentalNewApi
suspend fun getUsers(): List<User> {
   //... 
}
```

- 안정적인 API의 일부를 변경해야 한다면, 전환하는 데 시간을 두고 `Deprecated` 어노테이션을 활용해 사용자에게 미리 알려주는 것이 좋음

```kotlin
@Deprecated("User suspending getUsers instead")
fun getUsers(): List<User> {
   //... 
}
```

- 또한 직접적인 대안이 있는 `ReplaceWith` 경우 IDE에서 자동 전환을 허용하도록 지정

```kotlin
@Deprecated("User suspending getUsers instead", ReplaceWith("getUsers()"))
fun getUsers(callback: (List<User> -> Unit) {
   //... 
}
```

### 정리

- 사용자는 API 안정성을 알고 써야함
- 모듈 또는 라이브러리 작성자와 해당 사용자 간의 올바른 통신이 중요
- 또한 안정적인 API의 변경사항을 적용하려면 충분한 시간을 가져야 함

## Item29. 외부 API를 wrap해서 사용하라

잠재적으로 불안정하다고 판단되는 외부 라이브러리 API를 Wrap해서 사용하면 자유와 안정성을 얻을 수 있음

**Wrapper의 장점**

- 문제가 있다면 Wrapper만 변경하면 되므로 API 변경에 쉽게 대응 가능
- 프로젝트의 스타일에 맞춰서 API의 형태를 조정할 수 있음
- 특정 라이브러리에서 문제가 발생하면, 래퍼를 수정해서 쉽게 변경 가능
- 필요한 경우 쉽게 동작을 추가하거나 수정할 수 있음

**Wrapper의 단점**

- Wrapper를 따로 정의해야함
- 다른 개발자가 프로젝트를 다룰 때, 어떤 Wrapper들이 있는지 따로 확인해야함
- Wrapper는 프로젝트 내부에만 유효하므로 문제가 생겨도 질문하기 어려움

**정리**

- 이외의 장단점들을 비교해 Wrap할 API를 적절하게 결정해야함.
- 라이브러리가 얼마나 안정적인지 알려주는 좋은 방법은 version 번호와 사용자 수이기 때문에 이를 참고해 사용할 라이브러리 및 wrap할 API를 결정하면 좋음

## Item 30. 요소의 가시성을 최소화하라

API를 설계할 때 가능한 한 간결하고 가시성이  API를 선호하는데 그 이유는 아래와 같음

- 작은 인터페이스는 배우기 쉽고 유지하기 쉬움
    - 눈에 보이는 요소가 적을 수록 유지하고 테스트 해야할 것이 적음
- 변경을 가할 때 기존의 것을 숨기는 것보단 새로운 것을 노출하는 것이 쉬움
    - public 으로 공개해놓은 API는 외부에서 사용되는데 요소를 변경하는 경우에 모든 곳에서 업데이트 해야하기 때문에 가기성을 제한하는 것이 훨씬 더 어려워 각 용도를 신중하게 고려해 대안을 제공하는 것이 좋음
- 클래스의 상태를 외부에서 직접 변경할 수 있다면 클래스는 자산의 상태를 보장할 수 없음
    - 불변성이 깨질 수 있는 가능성이 있기 때문에 위험함
- 가시성이 제한될수록 클래스의 변경을 쉽게 추적할 수 있음
    - 프로퍼티의 상태를 더 쉽게 이해할 수 있고 동시성 처리에 용이함

### **가시성 한정자 사용하기**

**접근제어자**

- public : 모든 곳에서 사용 가능
- private : 클래스 내에서만 사용 가능
- protected : 해당 클래스와 하위 클래스에서만 사용 가능
- internal : 모듈 내부에서만 사용 가능

**Top-level 요소**

- public : 모든 곳에서 사용 가능
- private : 같은 파일 내부에서만 사용 가능
- internal : 모듈 내부에서만 사용 가능

### 정리

- 인터페이스가 작을수록 학습과 유지보수에 쉬움
- 최대한 제한이 되어야 변경하기 쉬움
- 클래스의 상태를 나타내는 프로퍼티가 노출되어 있다면 클래스가 자신의 상태를 책일질 수 없음
- 가시성이 제한되면 변경을 쉽게 추적할 수 있음

## Item 31. 문서로 규약을 정의하라

- 함수가 무엇을 하는지 명화갛게 설명하고 싶다면 KDoc 주석을 붙여주는 것이 좋음

```kotlin
/**
* 이 프로젝트에서 짧은 메시지를 사용자에게 출력할 때 사용하는 기본 방식
* @param message 사용자에게 보여 줄 메시지
* @param length 메시지의 길이가 어느정도 되는지 나타내는 enum 값
*/
fun Context.showMessage(
    message: String,
    duration: MessageLength = MessageLength.LONG,
) {
    val toastDuration = when (duration) {
        MessageLength.SHORT -> Length.LENGTH_LONG
        MessageLength.LONG -> Length.LENGTH_LONG
    }
    
    Toast.makeText(this, message, toastDuration).show()
}
```

- 행위가 문서화되지 않고 요소의 이름이 명확하지 않다면 이를 사용하는 사용자가 의도했던 추상화 목표가 아닌 현재 구현에 의존할 수 있기 때문에 예상되는 행위를 문서로 설명하면 좋음

### 규약

- 어떤 행위를 설명하면 사용자는 이를 일종의 약속으로 취급하며 이를 기반으로 스스로 자유롭게 생각하던 예측을 조정
- 이처럼 예측되는 행위를 요소의 규약이라고 부름
- 잘 짜여진 규약은 클래스를 만든 사람은 클래스가 어떻게 사용될지 걱정 안해도 됨

**규약 정의하기**

- 이름 : 일반적인 개념과 관련된 메소드는 이름만으로 동작을 예측할 수 있음
- 주석과 문서 : 필요한 모든 규약을 적을 수 있는 방법
- 타입 : 자주 사용되는 타입의 경우에는 타입만 보아도 어떻게 사용하는지 알 수 있지만 일부 타입은 문서에 추가로 설명해야할 필요가 있음

### 주석

- 로버트 마틴  : 주석없이 읽을 수 없는 코드를 짜야함
- 하지만 모든 일을 극단적으로 진행하는건 좋지 않고 주석을 적절하게 활용하면 더 많은 내용의 규약을 설명할 수 있음
- `listOf` 와 같이 코틀린 표준 라이브러리에 적힌 주석은 코드를 이해하는데 도움이 됨

### KDoc

주석을 사용하여 기능을 문서화 할 때 주석을 표시하는 공식 형식

`/**` 로 시작해서 `*/` 로 끝남

**주석 구조**

- 첫번째 줄 : 기능 요약 설명
- 두번째 줄 : 기능 자세한 설명
- 그 뒤에 모든 줄 : `@` tag로 시작, 파라미터를 설명하는데 사용
    - `@param <name>` : 함수의 값 매개변수 또는 클래스를 문서화
    - `@return` : 함수의 리턴 값
    - `@constructor` : 클래스의 기본 생성자
    - `@receiver` : 익스텐션 함수의 리시버
    - `@property <name>` : 명확한 이름을 갖고 있는 클래스의 프로퍼티를 문서화
    - `@throw <class>`, `@exception <class>` : 메소드 내부에서 발생할 수 있는 예외 사항
    - `@sample <identifier>` : 정규화된 형식 이름을 사용해서 함수의 사용 예를 문서화
    - `@author` : 요소의 작성자를 지정
    - `@since` : 요소에 대한 버전을 지정
    - `@supress` : 이를 지정하면 만들어진 문서에서 해당 요소가 제외
- …

### 타입 시스템과 예측

- 클래스와 인터페이스에도 여러 가지 예측이 들어가는데 클래스가 어떤 동작을 할 것이라 예측되면 서브 클래스도 이를 보장해야함
    - 이를 리스코프 치환 원칙이라고 부름
    - 해당 원칙은 객체 지향 프로그램에서 중요한데, S가 T의 서브 타입이라면 별도의 변경 없어도 T 타입 객체를 S 타입 객체로 대체할 수 있어야함

### 조금씩 달라지는 세부 사항

- 구현의 세부 사항은 항상 달라질 수 있지만 최대한 많이 보호하는 것이 좋음
- 즉 캡슐화가 많이 적용될 수록 사용자가 구현에 신경을 많이 쓸 필요없고 자유를 많이 갖게 됨

### 정리

- 외부 API를 구현할 때는 규약을 잘 정의해야함
- 이러한 규약은 이름, 문서, 타입을 통해 구현할 수 있음

## Item 32. 추상화 규약을 지켜라

- 규약은 개발자들의 단순한 합의이기 때문에 한쪽에서 규약을 위반할 수 있음

```kotlin
class Employee {
    private val id: Int = 2
    override fun toString() = "User(id=$id)"
    private fun privateFunction() {
        println("Private function called")
    }
}

fun callPrivateFunction(employee: Employee) {
    employee::class.declaredFunctions
        .first { it.name == "privateFunction" }
        .apply { isAccessible == true }
        .call(employee)
}

fun changeEmployeeId(employee: Employee, newId: Int) {
    employee::class.java.getDeclaredField("id")
        .apply { isAccessible = true }
        .set(employee, newId)
}

fun main() {
    val employee = Employee()
    callPrivateFunction(employee)
    changeEmployeeId(employee, 1)
    println(employee)
} 
```

- 위와 같이 리플렉션을 활용하면 우리가 원하는 것을 열고 사용할 수 있는데 이런 코드는 private 프로퍼티와 private 함수의 이름과 같은 세부적인 정보에 매우 크게 의존하고 있기 때문에 변경에 매우 취약할 수 있음

### 상속된 규약

- 클래스르 상속받거나 인터페이스를 확장할때는 규약을 지키는 것은 더더욱 중요
- 예를 들어 equals와 hashCode 메서드를 가진 Any 클래스를 상속 받는데 이러한 메소드는 모두 우리가 반드시 존중하고 지켜야하는 규약이 있음
    - 만약 규약을 지키지 않는다면 객체가 제대로 동작 안할 수 있음
    - 아래와 같이 equals가 제대로 구현되지 않는다면 중복을 허용하게 되어 제대로 동작하지 않음

```kotlin
class Id(val id: Int) {
    override fun equals(other: Any?): Boolean = other is Id && other.id == id
}

fun main() {
    val set = mutableSetOf(Id(1))
    set.add(Id(1))    
    set.add(Id(1))
    println(set.size) // 3
}
```

### 정리

- 프로그램 안정성을 위해 규약을 최대한 지키는 것이 좋음
- 만약 규약을 깨야한다면 문서화를 잘해두는 것이 좋음
