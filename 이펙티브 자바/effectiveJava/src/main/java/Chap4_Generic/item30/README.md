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
