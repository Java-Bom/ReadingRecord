## 스트림 병렬화는 주의해서 적용하라

<u>병렬 스트림 파이프라인 프로그래밍 할 때 '안정성'과 '응답가능성'을 고려해야한다.</u>

```java
    static void mersenne(){
        primes().map(p -> new BigInteger(String.valueOf(2)).pow(p.intValueExact()).subtract(new BigInteger(String.valueOf(1))))
                .parallel()
                .filter(mersenn -> mersenn.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);
    }
    static Stream<BigInteger> primes(){ // 이 코드를 실행하면 무한으로 돌아간다.
        return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime);
    }
```

위 코드는 아무것도 출력하지 않고 종료되지도 않는다. 왜? 병렬화하는 방법을 찾을 수 없기 때문
Stream.iterate (무한스트림), 중간연산 limit이 포함되면 parellel()로 성능개선을 할 수 없음.



limit 연산의 특징
- CPU가 남으면 limit이 넘어가도 몇개 더 처리함
- 만약 앞으로의 연산이 지난 연산보다 훨씬 오래걸린다면 ? --> 끝나지 않을 수 있음.



**언제 스트림 병렬화의 효과가 좋을까? - 소스스트림**
1. 스트림의 소스가 ArrayList, HashMap, HashSet, ConcurrentHashMap 인스턴스
2. 배열, int, long 범위일 때 

**왜?**
1. 데이터를 정확하고 쉽게 나눈 뒤 여러개의 스레드에 분배하기 쉬움.
-> 나누는 작업은 Spliterator

2. **참조지역성**: 이웃한 원소의 참조가 메모리에 연속적으로 저장 
- 참조지역성이 낮으면 주메모리에서 캐시메모리로 전송될 때까지 스레드는 기다려야한다.
- 기본타입은 데이터자체가 메모리에 연속되어 저장되어있기 때문에 참조지역성이 가장 좋다. 


**언제 스트림 병렬화의 효과가 좋을까? - 중간연산**
1. 축소연산(reduction): 파이프라인에서 만들어진 모든 원소를 하나로 합친다. 
ex) reduce, min, max, count, sum, anyMatch, allMatch, nonMatch

collect는 가변축소를 수행하기 때문에 적합하지 않다. 

