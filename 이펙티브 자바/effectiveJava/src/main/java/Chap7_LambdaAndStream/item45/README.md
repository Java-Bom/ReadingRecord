## 아이템 45. 스트림은 주의해서 사용하라

### 스트림이란

1. 데이터 원소의 유한 혹은 무한 시퀀스
- 무한시퀀스
```java
static Stream<BigInteger> primes(){ // 이 코드를 실행하면 무한으로 돌아간다.
        return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime);
}
```
2. 스트림파이프라인
**소스스트림 - 중간연산 - 종단연산**
중간연산: 소스스트림, 혹은 이전의 중간연산의 결과스트림에 함수를 적용하거나 걸러내는 연산. ex) filter, sort, reduce 등
종단연산: 최종결과. ex) collect, max, forEach(대부분의 경우에 비권장)  

- Lazy Operation
스트림 파이프라인은 지연평가된다. 지연평가란 종단연산이 호출되기 전에는 중간 연산을 수행하지 않는 다는 의미이다.
지연평가를 통해 무한스트림을 다룰 수 있다. 종단연산이 없는 스트림은 아무 행위도 하지 않는 명령어이다.
 
> Lazy operations achieve efficiency. It is a way not to work on stale data. Lazy operations might be useful in the situations where input data is consumed gradually rather than having whole complete set of elements beforehand. For example consider the situations where an infinite stream has been created using Stream#generate(Supplier<T>) and the provided Supplier function is gradually receiving data from a remote server. In those kind of the situations server call will only be made at a terminal operation when it's needed.
https://www.logicbig.com/tutorials/core-java-tutorial/java-util-stream/lazy-evaluation.html

```java
    @DisplayName("스트림파이프라인은 지연평가된다.")
    @Test
    void stream(){
        Stream.of("2", 1, 2, "4")
                .filter((value) -> isEquals(value)) // 계속 탄다
                .forEach(System.out::println);


        Stream.of("2", 1, 2, "4")
                .filter((value) -> isEquals(value)); // 안탈것이다


    }

    private boolean isEquals(Serializable value) {
        System.out.println("-------------");
        return value.getClass().equals(Integer.class);
    }
```

### 스트림을 사용할 때 주의할 점
1. 잘 사용하면 깔끔해지지만 잘못 사용하면 오히려 가독성이 떨어진다.
2. 스트림은 람다를 자주 사용한다. 람다는 타입 이름을 자주 생략하기 때문에 매개변수 이름을 잘 짓는 것이 중요하다.
3. 가독성을 위해 도우미 메서드 사용을 적극 검토해야한다. 
4. char 값들을 스트림으로 사용하면 intstream이 나오기 때문에 변환이 필요하다. 사용을 삼가하자.
![image](https://user-images.githubusercontent.com/47847993/81258995-e8396080-9071-11ea-97c3-896de64f09d5.png)
5. 스트림 원소가 무엇인지를 알려줄 수 있도록 **복수명사를 사용하자**

### 언제 스트림?
처음 스트림을 사용할 때 언제 스트림을 적용해야할지 모르겠다면 일단 리팩토링하되, 기존코드보다 나아보일 때까지 하자.
1. 원소들의 시퀀스를 일관되게 변경할 때
2. 원소들의 시퀀스를 필터링할 때
3. 원소들의 시퀀스를 하나의 연산으로 결합할 때
4. 원소들의 시퀀스를 컬렉션에 모을 떄
5. 원소들의 시퀀스 중 조건을 만족하는 원소를 찾을 

단, 다음의 경우에선 스트림을 사용할 수 없다.
1. 지역변수를 수정할 필요가 있을 때.(람다에서는 지역변수 수정이 불가하다.)
![image](https://user-images.githubusercontent.com/47847993/81131998-ee531280-8f87-11ea-8bfa-d10407e4a1ca.png)
2. return으로 끝내거나, break, continue로 반복문을 조작해야 할 때. 예외를 던져야 할 때. 


### 스트림을 사용하기 어려울 때
원래의 값을 파이프라인 여러 단계에 걸쳐 사용해야 할 때
- 스트림은 하나의 중간연산을 거치면 원래의 값을 잃는다. 이 경우 순서를 바꿈으로써 해결될 여지도 있다. 
