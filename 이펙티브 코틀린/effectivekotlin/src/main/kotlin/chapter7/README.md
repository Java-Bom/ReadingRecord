## Item 49 하나 이상의 처리 단계를 가진 경우에는 시퀀스를 사용하라

### Iterable과 Sequence의 차이

```kotlin
interface Iterable<out T> {
    operator fun interator(): Interator<T>
}

interface Sequence<out T> {
    operator fun interator(): Iterator<T>
}
```

- Iterable과 sequence는 완전히 다른 목적으로 설계되어 완전히 다른 형태로 동작함
- Sequence는 지연(lazy) 처리를 함
    - Sequence 함수를 사용하면 새로운 시퀀스를 리턴하고 최종적인 계산은 toList 또는 count등 최종 연산이 이루어질때 수행
    - 아래 코드에서 map 과 sequence는 중간 연산으로 어떠한 연산 처리를 하지 않고 기존의 시퀀스를 필터링 및 매핑하는 데코레이터만 설치한 후 toList등 최종 연산할 때 연산이 이루어짐

    ```kotlin
    sequence.map { }.filter { }.toList()
    
    val seq = sequenceOf(1, 2, 3)
    val filtered = seq.filter { print("f$it"); it % 2 == 1 }
    println(filtered) // FilteringSequence@...
        
    val asList = filtered.toList()
    // f1 f2 f3
    println(asList) // [1 , 3]
        
    val list = listOf(1, 2, 3)
    val listFiltered =  list
            .filter { print("f$it"); it % 2 == 1 }
    // f1 f2 f3
    println(listFiltered) // [1 , 3]
    ```

- 반면 Iterable은 함수를 사용할때마다 연산이 이루어져 List를 만듬

```kotlin
public inline fun <T> Iterable<T>.filter(
    predicate: (T) -> Boolean
): List<T> {
     return filterTo(ArrayList<T>(), predicate)
}

public fun <T> Sequence<T>.filter(predicate: (T) -> Boolean): Sequence<T> {
    return FilteringSequence(this, true, predicate)
}
```

**Sequence의 장점**

- 자연스러운 처리 순서를 유지
- 최소한만 연산
- 무한 시퀀스 형태로 사용 가능
- 각각의 단계에서 컬렉션을 만들어 내지 않음

### 순서의 중요성

- Iterable 처리와 Sequence 연산의 순서가 달라지면 다른 결과가 나옴
    - Sequence 처리는 하나하나에 지정한 연산을 한번에 적용 (lazy order)
    - Iterable 처리는 요소 전체 대상으로 연산을 수행 (eager order)

```kotlin
sequenceOf(1, 2, 3)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("F$it, "); it * 2 }
        .forEach { println("E$it, ") }
// 출력 : F1, M1, E2, F2, F3, M3, E6,
    
listOf(1, 2, 3)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2 }
        .forEach { print("E$it, ") }
    
// 출력 : F1, F2, F3, M1, M3, E2, E6
```

![sequence1](https://user-images.githubusercontent.com/47904523/174421470-ddef70b3-8077-4a0a-b312-9ea3cebdcd31.png)

- 컬렉션 함수 사용하지 않고 고전적인 반복문과 조건문 활용해 Sequence 구현

```kotlin
for (e in listOf(1, 2, 3)) {
    print("F$e, ")
    if (e % 2 == 1) {
        print("M$e, ")
    }
    val mapped = e * 2
    print("E$mapped, ")
}
// 출력 : F1, M1, E2, F2, F3, M3, E6,
```

- 즉 sequence 처리는 element-by-element order로 훨씬 자연스러운 처리라고 볼 수 있음

### 최소 연산

- sequence는 중간 연산이라는 개념을 갖고 있어서 모든 요소에 적용할 필요가 없는 경우 시퀀스를 활용하면 불필요한 연산을 줄일 수 있음

```kotlin
(1..10).asSequence()
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2}
        .find { it > 5 }
    // 출력 : F1, M1, F2, F3, M3,

(1..10)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2 }
        .filter { it > 5 }
// 출력 : F1, F2, F3, F4, F5, F6, F7, F8, F9, F10
// M1, M3, M5, M7, M9,
```

![sequence2](https://user-images.githubusercontent.com/47904523/174421472-156215ce-ac7f-4b95-932e-5dfd20b4daf8.png)

### 무한 시퀀스

- 시퀀스는 실제로 최종 연산이 일어나기 전까지는 컬렉션에 어떠한 처리도 하지 않아 무한 시퀀스를 만들고 필요한 부분 까지만 값을 추출할 수 있음

**generateSequence 함수**

```kotlin
generateSequence(1) { it + 1 }
     .map { it * 2 } 
     .take(10)
     .forEach { print("$it, ") } 
// 출력: 2 ,4, 6, 8, 10, 12, 14, 16, 18, 20,
```

sequence 중단 함수로 요소를 지정

```kotlin
val fibonacci = sequence { 
        yield(1)
        var current = 1
        var prev = 1
        
        while (true) {
            yield(current)
            val temp  = prev
            prev = current
            current += temp
        }
    }
    
println(fibonacci.take(10).toList())
// [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
```

- 참고로 무한 시퀀스는 실제로 사용할 때는 값을 몇 개 활용할지 지정해야함
    - ex) `print(fibonacci.toList()) // 종료되지 않음`
- first, find, all 와 같은 종결 연산도 활용할 수 있는데 any는 true를 리턴하지 못하면 무한 반복에 빠지고 all과 none은 false를 리턴하지 못하면 무한 반복에 빠짐 결과적으로 무한 시퀀스는 종결 연산으로 take와 first정도만 사용하는 것이 좋음

### 각각의 단계에서 컬렉션을 만들어 내지 않음

- seqeuce는 각각의 단계에서 컬렉션을 만들지 않기 때문에 큰 컬렉션으로 여러 처리 단계를 거쳐야 한다면 컬렉션 처리보다 시퀀스 처리를 사용하는 것이 좋음

### 시퀀스가 빠르지 않은 경우

- 컬렉션 전체를 기반으로 처리해야 하는 연산은 시퀀스를 사용해도 빨라지지 않음
- 유일한 예로 stdilib의 sorted가 있는데 sorted는 Sequence를 List로 변환한 뒤에 자바 stdlib의 sort를 사용해 처리
- 이러한 변환 처리로 인해 시퀀스가 컬렉션 처리보다 느려짐
- 참고로 무한 시퀀스처럼 시퀀스의 다음 요소를 lazy하게 구하는 시퀀스에 sorted를 적용하면 무한 반복에 빠짐

```kotlin
generateSequence(0) { it + 1 }.take(10).sorted().toList()
// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
generateSequence(0) { it + 1 }.sorted().take(10).toList()
// 종료되지 않음. 따라서 어떤 값도 리턴하지 않음
```

### 자바 스트림의 경우

- 자바 8부터 컬렉션 처리를 위해 스트림 기능이 추가
- 코틀린의 시퀀스와 비슷한 형태로 동작

```kotlin
productList.asSequence()
     .filter { it.bought } 
     .map { it.price }
     .average()

productList.stream()
     .filter { it.bought } 
     .mapToDouble { it.price }
     .average()
     .orElse(0.0)
```

- 차이점
    - 코틀린의 시퀀스가 더 많은 처리 함수를 가지고 있음 (확장 함수가 정의되어 있으므로)
    - 자바 스트림은 병렬 함수를 사용해 병렬 모드로 실행 할 수 있고 이 점은 멀티 코어 환경에서 굉장히 큰 성능 향상을 가져옴
    - 코틀린 시퀀스는 코틀린/JVM ,코틀린/JS , 코틀린/ 네이티브등의 일반적인 모률에서 모두 사용할 수 있음

### 코틀린 시퀀스 디버깅

- Kotlin Sequence Debugger 활용

### 정리

- 컬렉션과 시퀀스는 같은 처리 메서드를 지원하며 사용하는 형태가 비슷한데 시퀀스는 lazy하게 처리
- 시퀀스의 장점은 아래와 같음
    - 자연스러운 처리 순서 유지
    - 최소한만 연산
    - 무한 시퀀스 형태 사용 가능
    - 각각의 단계에서 컬렉션을 만들지 않음

## Item 50. 컬렉션 처리 단계 수를 제한하라

- 모든 컬렉션 처리 메서드는 내부적으로 반복을 돌거나 추가적인 컬렉션을 생성하므로 비용이 많이 듬.
    - 시퀀스는 중간 연산이라는 개념이 존재하기는 하지만 시퀀스 전체를 랩하는 객체를 만들기 때문에 비용 발생
- 따라서 적절한 메서드를 활용해 컬렉션 처리 단계 수를 적절하게 제한하는 것이 좋음

```kotlin
fun List<Student>.getNames(): List<String> = this
    .map { it.name }
    .filter { it != null }
    .map { it!! }

// better
fun List<Student>.getNames(): List<String> = this
    .map { it.name }
    .filterNotNull()

// best
fun List<Student>.getNames(): List<String> = this
    .mapNotNull { it.name }
```

### Item 51. 성능이 중요한 부분에는 기본 자료형 배열을 사용

- 코틀린은 기본 자료형을 선언할 수 없지만, 최적화를 위해서는 내부적으로는 사용할 수 있음
- 기본 자료형의 특징
    - 가벼움 - 일반적인 객체와는 다르게 추가적으로 포함되는 값이 없음
    - 빠름 - 값에 접근할 대 추가 비용이 들지 않음
- 따라서 대규모의 데이터를 처리할 때 기본 자료형을 사용하면 상당한 큰 최적화가 이루어짐
- 그러나 코틀린에서 사용되는 List와 Set 등의 컬렉션은 제네릭 타입이기 때문에 기본 자료형을 사용할 수 없으므로 랩핑된 타입을 사용해야 함
- 하지만 성능이 중요한 코드라면 IntArray와 LongArray등의 기본 자료형을 활용하는 배열을 사용하는 것이 좋음

**메모리와 속도 비교**

- 메모리 차이
    - IntArray : 400,000,016 바이트
    - List<Int> : 2,000,006,944 바이트
    - 약 5배 차이
- 성능 차이
    - 1,000,000개의 숫자를 갖는 컬렉션을 사용해서 평균을 구하는 처리를 하면 배열을 사용하는 경우가 25% 정도 더 빠름

    ```kotlin
    open class InlineFilterBenchmark {
        
        lateinit var list: List<Int>
        lateinit var array: IntArray
        
        @SetUp
        fun init() {
            list = List(1_000_000) { it }
            array = IntArray(1_000_000) { it }
        }
        
        @Benchmark
        fun averageOnIntList(): Double {
            return list.average()
        }
    
        @Benchmark
        fun averageOnIntArray(): Double {
            return array.average()
        }
    }
    ```


### 정리

- 일반적으로 Array보다 List & Set를 사용하는 것이 좋지만 성능이나 메모리 사용량을 줄일려면 Array를 사용하는 것이 좋음

## Item 52. mutable 컬렉션 사용을 고려하라

- immutable 컬렉션보다 mutable 컬렉션이 좋은 점은 성능적 측면에서 더 빠르다는 점인데, immutable 컬렉션에 요소를 추가하려면 새로운 컬렉션을 만들려면 여기에 요소를 추가해야 함

```kotlin
public operator fun <T> Collection<T>.plus(element: T): List<T> {
    val result = ArrayList<T>(size + 1)
    result.addAll(this)
    result.add(element)
    return result
}
```

- 이처럼 immutable에 요소를 추가하는 과정은 처리 비용이 큼
- 그래서 이러한 복제 처리를 하지 않는 mutable 컬렉션이 성능적 관점에서 좋음
- 다만 item 1에서 이야기한 immutable이 안전성 측면에서 더욱 좋음
- 하지만 지역변수는 mutable 안정성 측면에서 언급한 동기화나 캡슐화 측면에서 문제가 발생하지 않으므로 지역 변수로 사용할 때는 mutable 컬렉션을 사용하는 것이 더 합리적일 수 있음