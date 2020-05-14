## 아이템 46. 스트림에서는 부작용 없는 함수를 사용하라


<u>스트림은 함수형 프로그래밍에 기초한 패러다임이다</u>


**모던 자바 인 액션 발췌**
### 함수를 값으로

메서드를 값으로 취급하여 활용성을 높인다.(일급 값)

1. 메서드 참조
2. 람다(익명함수)
- 함수형 프로그래밍: 함수를 일급값으로 넘겨주는 프로그래밍
3. 스트림  




### 스트림 패러다임의 핵심

각 변환 단계는 "순수함수"로 구성되어양 한다.
*순수함수: 입력만이 출력엥 영향을 준다*



### 다양한 스트림 연산

#### forEach
forEach 연산은 스트림 계산 결과를 보고할 때만 사용하고, 계산할 때는 사용하지 말자.
-> forEach 연산에서 계산하는 것은 덜 스트림스럽다. 



#### Collector 

수집기(collector)를 잘 활용하자. 
- 축소(reduction) 전략을 캡슐화한 블랙박스 객체
- reduction: 원소들을 객체 하나에 취합한다는 뜻 


1. 맵 수집기(toMap)

ㄱ. 각각 KeyMapper, ValueMapper 를 인수로하는 가장 간단한 맵 수집기. Key 가 중복되면 Exception. 
```java
toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper)
Returns a Collector that accumulates elements into a Map whose keys and values are the result of applying the provided mapping functions to the input elements.
```

```java
    @DisplayName("맵수집기 - 각 원소당 하나의 키")
    @Test
    void toMap_Test() {
        Map<String, Operation> expectedMap = new HashMap<>();
        expectedMap.put("PLUS", Operation.PLUS);
        expectedMap.put("MINUS", Operation.MINUS);
        expectedMap.put("DIVIDE", Operation.DIVIDE);

        Map<String, Operation> collect = Stream.of(Operation.values())
                .collect(toMap(Objects::toString, e -> e));

//        Map<String, Operation> failCollect = Stream.of(Operation.values())
//                .collect(toMap(e -> "SameKey", e -> e));  // java.lang.IllegalStateException: Duplicate key SameKey (attempted merging values PLUS and MINUS)

        assertThat(collect).isEqualTo(expectedMap);

    }
```


ㄴ. KeyMapper, ValueMapper, 두 원소가 충돌했을 때의 병합함수
```java
toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction)
Returns a Collector that accumulates elements into a Map whose keys and values are the result of applying the provided mapping functions to the input elements.
```

```java
    @DisplayName("맵수집기 - 인수가 세개")
    @Test
    void toMap_Test_Merge() {
        Map<String, Operation> expectedMap = new HashMap<>();
        expectedMap.put("SameKey", Operation.PLUS);

        Map<String, Operation> collect = Stream.of(Operation.values())
                .collect(toMap(e -> "SameKey", e -> e, (a, b) -> a));

        assertThat(collect).isEqualTo(expectedMap);

    }
```

ㄷ. map의 구현체를 정할 수 있다. 
```java
toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier)
Returns a Collector that accumulates elements into a Map whose keys and values are the result of applying the provided mapping functions to the input elements.
```

```java
    @DisplayName("맵수집기 - 인수가 네개")
    @Test
    void toMap_Test_Four() {
        Map<Operation, String> expectedMap = new EnumMap<>(Operation.class);
        expectedMap.put(Operation.PLUS, "PLUS");
        expectedMap.put(Operation.MINUS, "MINUS");
        expectedMap.put(Operation.DIVIDE, "DIVIDE");

        EnumMap<Operation, String> collect = Stream.of(Operation.values())
                .collect(toMap(e -> e, Object::toString, (a, b) -> a, () -> new EnumMap<>(Operation.class)));

        assertThat(collect).isEqualTo(expectedMap);

    }
```




2. groupingBy

ㄱ. classfier(분류함수)를 받고 카테고리로 묶은 Map을 담은 수집기 반환. 값은 List
```java
	groupingBy(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream)
Returns a Collector implementing a cascaded "group by" operation on input elements of type T, grouping elements according to a classification function, and then performing a reduction operation on the values associated with a given key using the specified downstream Collector.
```

```java
@DisplayName("groupBy 의 기본쓰임")
    @Test
    void groupByBasic() {
        //given
        List<String> dictionary = Arrays.asList("apple", "apartment", "banana", "bigbang", "count", "cleancode");

        //when
        Map<Character, List<String>> collect = dictionary.stream()
                .collect(groupingBy(word -> word.toLowerCase().charAt(0)));
    }
```


ㄴ. 값을 리스트 외 다른 타입을 반환하기 위해서는 downstream명시. 다운스트림 수집기의 역할은 해당카테고리의 원소들을 담은 스트림으로부터 값을 생성하는 일
```java
groupingBy(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream)
Returns a Collector implementing a cascaded "group by" operation on input elements of type T, grouping elements according to a classification function, and then performing a reduction operation on the values associated with a given key using the specified downstream Collector.
```

```java
@DisplayName("groupby + downstream")
    @Test
    public void groupByDownStream() {
        //given
        List<String> dictionary = Arrays.asList("apple", "apartment", "banana", "bigbang", "count", "cleancode");

        //when
        Map<Character, Long> collect = dictionary.stream()
                .collect(groupingBy(word -> word.toLowerCase().charAt(0), counting()));
    }
```

ㄷ. 다운스트림, 맵 팩터리 지정
- ㄴ에 의해 맵팩터리 인수는 세번째에 와야하짐나 두번째에 온다. 점층적 인수 목록 패턴에 어긋난다.
```java
groupingBy(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream)
Returns a Collector implementing a cascaded "group by" operation on input elements of type T, grouping elements according to a classification function, and then performing a reduction operation on the values associated with a given key using the specified downstream Collector.
```

```java
    @DisplayName("groupby + 맵팩터리")
    @Test
    void groupByMapFactory() {
        //given
        List<String> dictionary = Arrays.asList("apple", "apartment", "banana", "bigbang", "count", "cleancode");

        //when
        Map<Character, Long> collect = dictionary.stream()
                .collect(groupingBy(word -> word.toLowerCase().charAt(0), TreeMap::new, counting()));
    }
```


3. 그 외 다양한 메서드

```java
elements.stream().collect(Collectors.summingInt(a -> a * a));
// mapToInt().sum() 과 동일 

IntSummaryStatistics collect = elements.stream().collect(Collectors.summarizingInt(a -> a + a));
//IntSummaryStatistics{count=6, sum=42, min=2, average=7.000000, max=12}
 
```

+ joining, reducing, filtering, mapping, flatMapping, collectingAndThen
```java
    Integer collect = elements.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collection::size));
    // collect한 뒤 변환까지
```
