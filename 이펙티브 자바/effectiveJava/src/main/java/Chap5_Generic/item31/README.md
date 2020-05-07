## 아이템 31. 한정적 와일드카드를 사용해 API 유연성을 높여라



#### 스택 예제


```java
package Chap5_Generic.item31;

import java.util.ArrayList;
import java.util.List;

public class Stack<E> { // 여기의 E와
    private List<E> list = new ArrayList<>();

    public void pushAll(Iterable<E> src){ // 여기의 E는 같아야함. 유연하지 못함.
        for(E e : src){
            push(e);
        }
    }

    public void push(E e){
        list.add(e);
    }
}
```

```java
    @DisplayName("Number Stack에 Integer를 넣을 수 있는지")
    @Test
    void stacktest(){
        Stack<Number> stack = new Stack<>();
        List<Integer> intList = Arrays.asList(1,2,3,4);
//        stack.pushAll(intList); Compile Error
    }
```


스택 코드를 봤을 때, 논리적으로는 Number를 담을 수 있는 스택이 Integer도 담을 수 있어야 할 것 같다.
하지만 제네릭은 불공변이기 때문에 Integer가 Number의 하위타입인 것은 전혀 상관이 없다.
유연한 스택을 만들기 위해 한정적 와일드 타입을 쓸 수 있다.

```java
public void pushAll(Iterable<? extends E> src){
        for(E e : src){
            push(e);
        }
}
```

위와 같이 E의 하위타입은 어떤 것이든 올 수 있도록 수정하면, 기존의 테스트코드는 컴파일에러가 나지 않는다.





stack의 모든 원소를 매개변수로 주어진 Collection으로 옮겨서 반환하는 아래의 메서드가 있다.
```java
    public void popAll(Collection<E> dst) {
        dst.addAll(list);
        list.clear();
    }
```


```java
    @DisplayName("popAll 은 같은 타입의 컬렉션에만 담을 수 있다")
    @Test
    void popAll(){
        Stack<Number> stack = new Stack<>();
        List<Integer> intList = Arrays.asList(1,2,3,4);
        stack.pushAll(intList);

        List<Object> objStore = new ArrayList<>();
        List<Number> store = new ArrayList<>();
        stack.popAll(store);
//        stack.popAll(objStore); ComplileError

        assertThat(store).contains(1,2,3,4);
    }
```

Object 스토어는 Number를 논리적으로는 담을 수 있을 것 같지만 역시나 제네릭은 불공변이기 때문에 불가능하다. 

```java
public void popAll(Collection<? super E> dst) {
        dst.addAll(list);
        list.clear();
    }
```
이때, 원소를 담을 컬렉션의 타입을 E의 수퍼타입으로 제한하도록 하면, 모든 Number 수퍼타입 객체컬렉션은 stack의 원소를 담을 수 있다.




결론) 유연성을 극대화 하기 위해 생산자, 소비자용 입력 매개변수에는 와일드타입을 이용하자.
**Get and Put Principle "E의 생산자는 ? extends E", "E의 소비자는 ? super E"**


주의할 점) 반환값에는 한정적 와일드 타입을 쓰면 안된다. 클라이언트에서도 그걸 계속 고려해야하니까.




```java
// item 30
public static <E extends Comparable<E>> E max(Collection<E> c);

// Get and Put Principle 적용
public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) 
```

max 메서드는 매개변수를 받아 T를 생산하여 반환한다. -------> ? extends T 
그리고 Comparable<T> 는 T 인스턴스를 소비한다 ----------> ? super T (Comparable, Comparator 항상 소비자)





### 타입 매개변수 VS 와일드카드 

1. 메서드 선언에 타입 매개변수가 한번만 나오면 와일드카드로 대체하자

```java
// 이것도 어차피 어떤 원소도 넣을 수 있고
public static <E> void swap(List<E> list, int target, int dest);

// 얘도 그런데, 굳이 복잡한 private 도우미메서드가 왜필요한거지 ,, 
public static void swap(List<?> list, int target, int dest);
```


와일드카드는 null 이외의 원소는 넣을 수 없다. 

+ private 도우미 메서드를 따로 작성.



