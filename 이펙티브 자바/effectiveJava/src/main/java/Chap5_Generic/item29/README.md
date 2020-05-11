## 아이템 29. 이왕이면 제네릭타입으로 만들라


### 요약

클라이언트가 사용할 때 매번 형변환을 해야하고 그 과정에서 Runtime 에러가 나는 것을 방지하기 위해 이왕이면 제네릭 타입으로 만들자.



### Object 배열로 이루어진 스택


```java
public class ArrayStack {
    private Object[] elements;
    private int size;

    public ArrayStack() {
        this.elements = new Object[10];
    }

    public void push(Object obj) {
        elements[size++] = obj;
    }

    public Object pop() {
        if (size == 0) {
            throw new IllegalArgumentException("스택에 아무것도 없습니다");
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

}
```


위와 같이 Object 배열을 가진 스택을 사용해보자



```java
 @DisplayName("Object리스트타입의 스택은 매번 형변환이 필요하다")
    @Test
    void basExample() {
        Bad bad = new Bad("BadExample1");
        ArrayStack arrayStack = new ArrayStack();
        arrayStack.push(bad);

//        Bad findBad = arrayStack.pop(); CompileError!
//        String maybeBad = (String) arrayStack.pop(); RunTimeError!
    }

    class Bad {
        private String value;

        public Bad(String value) {
            this.value = value;
        }
    }
```



클라이언트는 매번 형변환을 해야하고, 형변환이 반드시 성공할 것이라는 보장이 없기 때문에 Runtime Exception 이 나올 수 있다.



이제 이 스택클래스를 제네릭으로 바꿔보며 제네릭으로 바꿨을 때의 장점을 알아볼 것이다



먼저, 모든 Object를 제네릭 E로 변경하면 다음과 같이 컴파일 에러가 발생한다.

![image](https://user-images.githubusercontent.com/47847993/77606329-8659e700-6f5a-11ea-99bc-b1455786b777.png)



E는 소거되기 때문인데, 컴파일 에러를 해결할 수 있는 방법은 2가지가 있다.




1. 대놓고 우회하는 방법: 생성자의 컴파일 에러를 다음으로 해결한다.
```java
    public GenericStack() {
        this.elements = (E[])new Object[10];
    }
```



이 예제의 경우 E타입만 Push할 수 있기 때문에 자체적으로 형변환이 늘 안전하다는 것을 확신할 수 있다.
따라서 이렇게 우회하고 @SuppressWarnings 으로 비검사 오류를 숨길 수 있다.

장점: 필드에 E 배열 타입을 가지고 있음으로써 이 Stack은 E 타입의 인스턴스만 받을 수 있음을 확신할 수 있도록 한다. 
단점: 힙 오염. 런타임타입: E, 컴파일타임 타입: Object

*cf) 힙오염(https://en.wikipedia.org/wiki/Heap_pollution)*
*아래와 같이 컴파일타임타입, 런타임타입이 달라 Unchecked Warning 과 ClassCastException 이 발생할 가능성이 있는 상황.*
*스택 예의 경우 그럴 가능성은 없음*



```java
public class HeapPollutionDemo
{
   public static void main(String[] args)
   {
      Set s = new TreeSet<Integer>();
      Set<String> ss = s;            // unchecked warning
      s.add(new Integer(42));        // another unchecked warning
      Iterator<String> iter = ss.iterator();
      while (iter.hasNext())
      {
         String str = iter.next();   // ClassCastException thrown
         System.out.println(str);
      }
   }
}
```

2. 필드를 Object[] 으로 바꾸는 법: pop 메서드에 형변환과 @SuppressWarnings 를 추가한다.
```java
        @SuppressWarnings("unchecked")
        E result = (E) elements[--size];
```


장점: 힙오염 없음
단점: 원소를 pop 할 때마다 형변환
