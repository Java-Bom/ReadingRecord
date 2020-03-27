## 이왕이면 제네릭 메서드로 만들라


#### 매개변수화 타입을 받는 정적 유틸리티 메서드는 보통 제네릭이다

**ex) Collections.binarySearch 메서드**
```java
public static <T>
    int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        if (list instanceof RandomAccess || list.size()<BINARYSEARCH_THRESHOLD)
            return Collections.indexedBinarySearch(list, key);
        else
            return Collections.iteratorBinarySearch(list, key);
    }
```

모든 원소가 T의 수퍼타입과 비교 가능한 Comparable을 상속하고 있는 list와 T타입 key 를 비교한다.




*cf) <? extends T> vs <? super T>*
https://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java


```java
List<? extends Number> numbers = new ArrayList<Integer>();
List<? extends Number> numbers = new ArrayList<Double>();
```
You can't add any object to List<? extends T> because you can't guarantee what kind of List it is really pointing to, so you can't guarantee that the object is allowed in that List. The only "guarantee" is that you can only read from it and you'll get a T or subclass of T.

```java
List<? super Integer> intLists = new ArrayList<Integer>();
List<? super Integer> intLists = new ArrayList<Number>();
List<? super Integer> intLists = new ArrayList<Object>();
```


### 제네릭을 만드는 방법


1. 단순한 제네릭 메서드 - 모든 매개변수타입이 같아야한다
```java
 public static <E> List<E> union(List<E> list1, List<E> list2) {
        List<E> allList = new ArrayList<>();
        allList.addAll(list1);
        allList.addAll(list2);
        return allList;
    }
```

<u>유연성이 떨어짐</u>

```java
  @DisplayName("단순한 제네릭 메서드 사용법")
    @Test
    void generic(){
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6);
        assertThat(GenericMethod.union(list1, list2)).isEqualTo(Arrays.asList(1,2,3,4,5,6));

        List<String> list3 = Arrays.asList("str1", "str2");
//        assertThat(GenericMethod.union(list1, list3)).isEqualTo(Arrays.asList(1,2,3,4,5,6)); compileError
    }
```




2. 불변객체가 제네릭타입이면 여러 타입으로 활용할 수 있게된다. 


: 요청 타입 변수에 맞게 객체의 타입을 바꿔주는 **"제네릭싱글턴팩터리"** 필요


제네릭 싱글턴 팩터리를 사용하는 예 1) 함수객체 Collections.revoerseOrder()

```java
List<String> str = Arrays.asList("a", "b", "c");
        str.sort(Collections.reverseOrder());
```

```java
public static <T> Comparator<T> reverseOrder() {
        return (Comparator<T>) ReverseComparator.REVERSE_ORDER; // T 타입으로 캐스팅된 Singleton ReverseComparator 반환, 타입정보는 소거됨으로 그떄그떄 캐스팅만 되는 것
}

private static class ReverseComparator
        implements Comparator<Comparable<Object>>, Serializable {

        private static final long serialVersionUID = 7207038068494060240L;

        static final ReverseComparator REVERSE_ORDER
            = new ReverseComparator();

        public int compare(Comparable<Object> c1, Comparable<Object> c2) {
            return c2.compareTo(c1);
        }

        private Object readResolve() { return Collections.reverseOrder(); }

        @Override
        public Comparator<Comparable<Object>> reversed() {
            return Comparator.naturalOrder();
        }
    }
```

```java
 public static final <T> Set<T> emptySet() {
        return (Set<T>) EMPTY_SET;  // T 타입으로 변환해서 반환해주는 제네릭싱글턴팩터리
    }

 public static final Set EMPTY_SET = new EmptySet<>(); // 싱글턴객체
```



제네릭 싱글턴 팩터리를 사용하는 예 2) 재귀적 타입 한정 



```java
public static <E extends Comparable<E>> E max(Collection<E> c);
```

'모든타입 E는 자기자신과 같은 타입인 원소 모두와 비교가능하다' 라는 추측이 가능해짐 




*cf) 시뮬레이트한 셀프타입 관용구 - 아이템 2*
재귀적 타입 한정을 이용하는 제네릭타입. 
추상메서드 self 를 지원하여 하위클래스에서 형변환 하지 않고도 메서드 연쇄를 지원할 수 있음. 
self 타입이 없는 자바를 위한 우회방법


상위 클래스에서 재귀적 타입한정 클래스 Builder를 추상클래스로 제공

```java
public abstract class PayCard {
    public enum Benefit {
        POINT("포인트"), SALE("할인"), SUPPORT("연회비지원");
        Benefit(String benefit) {
        }
    }

    final Set<Benefit> benefits;

    abstract static class Builder<T extends Builder<T>> { // 재귀적 타입한정
        EnumSet<Benefit> benefits = EnumSet.noneOf(Benefit.class);

        public T addBenefit(Benefit benefit) {
            this.benefits.add(benefit);
            return self();
        }

        abstract PayCard build();

        protected abstract T self();
    }

    PayCard(Builder<?> builder) {
        benefits = builder.benefits.clone();
    }
}

```
     
     
하위클래스에서는 아래와 같이 재구현입. 타입캐스팅 없음
```java
    public static class Builder extends PayCard.Builder<Builder>{
        private final Sale sale;

        public Builder(Sale sale) {
            this.sale = sale;
        }

        @Override
        LottePayCard build() {
            return new LottePayCard(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
```


아래와 같이 상위 클래스의 메서드 호출 가능
```java
    @DisplayName("시뮬레이트한 셀프타입 관용구")
    @Test
    void simulate(){
        LottePayCard lottePayCard = new LottePayCard.Builder(LOTTE_DEPT)
                .addBenefit(PayCard.Benefit.POINT)
                .build();
    }
```