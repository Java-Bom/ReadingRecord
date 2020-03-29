제네릭 아이템26,27,28
=================

 Item 26  로타입은 사용하지 마라
   ----------------

   ### 로타입은 왜 사용하면 안될까?

   > 로타입은 **List list = new ArrayList();** 형태로 사용하는 방식이다.  
   > 이번장에서 왜 **로타입을 사용하면 안되는지**, 그렇다면 로타입을 사용하고 싶을땐 **어떤 방법으로 제네릭**을 사용해야하는지 알아보자.

   </br>
   </br>

   #### 로타입은 뭐든지 다 들어간다.
----------------------------

```java
         @Test
             @DisplayName("로타입은 뭐든지 다 들어간다.")
             void rowType() {
                 List list = new ArrayList();
                 list.add("문자열");
                 list.add(1);

                 String text = (String) list.get(0);
                 Integer number = (Integer) list.get(1);

                 assertThat(text).isEqualTo("문자열");
                 assertThat(number).isEqualTo(1);
             }
```

  로타입은 문자열,숫자 가리지 않고 다 넣을 수 있다. 위 코드에서도 문제없이 동작할 것이다.  
  *"뭐든지 다 넣을 수 있으면 좋은거 아니야?"* 라고 생각할 수 있지만 전혀 아니다.            
  리스트에서 나올 값을 항상 올바른 타입으로 캐스팅해줘야 한다는 점은 사용자 입장에서 굉장히 부담스러울 것이다.  
  로타입을 사용하는 것은 제네릭이 자랑하는 **타입안전성**과 **표현력**을 모두 잃게 된다.     

```java
      @Test
      @DisplayName("로타입은 뭐든지 다 들어간다.")
      void rowType2() {
          List<String> stringList = new ArrayList();
          unsafeAdd(stringList,new Integer(1));

          assertThatThrownBy(()->stringList.get(0))
                  .isInstanceOf(ClassCastException.class);

      }

      private void unsafeAdd(List list,Object o){
          list.add(o);
      }
```


   이런 경우도 있을 수 있다. 매개형식타입으로 List<String> stringList로 잘 선언했지만,  
   로타입 리스트와 오브젝트를 받아 add시켜주는 함수를 이용해 add한다면 컴파일은 되지만 get을 할때  
   **ClassCastException**이 떨어질 것이다.      
   </br>
   </br>

   #### 로타입이 쓰고 싶을땐 어떻게 하지?
-------------------------------
 분명 로타입을 사용하고 싶은 요구사항도 있을 것이다.  
 로타입의 두 Set끼리 비교하여 같은것이 몇개 포함되었는지 비교하는 함수를 만든다면, 그 함수는 어떤 매개변수를 가지던지 상관하지 않고 싶을 것이다.
          
```java
     private int count(Set destination, Set source) {
         int result = 0;
         for (Object o : source) {
             if (destination.contains(o)) {
                 result++;
             }
         }
         return result;
      }
```

만약 로타입을 사용하지 않는다면 필요한 타입들에 대한 함수들을 모두 만들어야 할 것이다.            

```java
      private int count(Set<Object> destination, Set<String> source) {
          ....
          return result;
      }

      private int count(Set<Object> destination, Set<Integer> source) {
          ....
          return result;
       }
```
</br>

이럴 경우에는 비한정적와일드카드타입을 활용해 해결한다.
**비한정적 와일드카드 타입**은 어떤 타입이든 상관없이 받을 수 있지만 타입의 안정성을 보장해준다.  
또 로타입과 달리 비한정적 와일드카드 타입은 null외에 어떤 원소도 넣을 수 없어 불벽식을 보장한다.  
이런 요구사항에서는 **비한정적 와일드카드 타입**을 쓰도록 하자.
 </br>


```java
     private int count(Set<?> destination, Set<?> source) {
         ....
         return result;
     }
```


     주의사항.   
     List<Object>와 List<String>은 공변관계가 아니다.   
     Object가 String의 상위 클래스라 해서 매개변수타입에서도 그런 규칙을 따르는 것은 아니다.  
     이 관계에서는 서로 다른 타입이라고 생각하면 된다.        



</br>
</br>

#### 로타입을 써도 좋은 곳
-------------------------------

1. 클래스 리터럴에는 로타입을 사용해도 좋다.  

클래스 리터럴에 **매개변수화** (List<String>) 타입을 사용하지 못하기 때문이다.  
List.class,String[].class는 가능, List<String>.class는 불가능!

2. instanceof에도 사용해도 좋다.  

**런타임**에는 **제네릭 타입** 정보가 지워진다.  
그래서 instanceof에서 리스트타입인지 알고싶다면, 로타입이든 비한정적 와일드 타입이든 둘중 하나를 사용해라.

</br>
</br>


>*런타임에는 제네릭 타입정보가 사라진다?*

>제네릭 타입정보는 컴파일 타임에 검사하고 런타임에는 타입정보가 사라진다.  
>![list-get](./image/list-get.png).
>
>위 사진은 ArrayList에서 get하는 부분의 코드다.   
>위와같이 제네릭 타입으로 캐스팅하여 나오는 코드는 컴파일 타임에 자동으로 제네릭 타입으로 형변화하도록 코드를 심어넣은것이다.   







## Item 27 비검사 경고를 제거하라
----------------------------

> 제네릭을 사용하기 시작하면 수많은 컴파일러 경고들을 보게된다.
>
> 이번 장은 제네릭 사용시 제거할 수 있는 경고들을 알아보자.



#### 제거할 수 있는 경고는 제거하자!

--------------------------



인텔리제이는 컴파일 과정을 대신해주기 때문에 **컴파일 로그 **를 보거나 할 일이 없다.

대신 인텔리제이는 **컴파일 경고**가 날 수 있는 부분을 코드상에서 미리 알려준다.



![비검사경고](./image/비검사경고.png)



```java
List<String> strings = new ArrayList(); 
```

위 코드는 타입추론이 힘들다. 

<>다이아몬드를 추가해주면 타입추론이 가능해  컴파일단계에서 경고를 삭제할 수 있다.





### 제거할 수 없는 경고는 숨기자

--------------------------------------------------



타입이 안전하다고 확신이 들면 제거할 수 있는 경고들은 숨기자!



```java

private E[] = new Object[n];

public void push(E element){
  element[i] = e;
}

public E pop(){
  return (E) elements[i];
}


```



위 약식 코드를 보면 **퍼블릭 인터페이스**는 모두 컴파일 타임에 정해진 타입 검사를 마치고 런타임에는 **타입검사가** 없어질 것이다.

위 코드는 **컴파일 타임**에서 pop함수는 Object를 **E로 캐스팅해 넘기는 부분**에서 ClassCastException이 발생할 수 있으므로 경고할 것이다.

하지만 Object안에 값을 넣는 인터페이스는 push 하나뿐이므로 Object는 언제나 **E타입의 동일한 값**만 들어가므로 **타입 safe**하다.

그러므로 pop에서 발생할 수 있는 **경고를 제거**해주는 것이좋다.



```java
public E pop(){
  @SuppressWarnings("unchecked")
	E e=  (E) elements[i];
  return e;
}
```



**SuppressWarnings 어노테이션**은 경고를 감춰준다. 

해당 어노테이션은 선언에만 달 수 있으므로 **함수선언, 변수선언** 등에 달 수 있다. (return에는 달 수 없다.)

그래서 위와같이 **E를 꺼내는 부분을 선언으로 바꿔서 e를 리턴하므로** 최대한 좁은 범위로 어노테이션을 달아놓은 것이다.

* 실제 위 코드는 함수에 어노테이션을 붙이는 것이 좋지만, 최대한 좁은 범위를 어필하기 위해 저런 식으로 만들었다.

만약 pop코드에 **ClassCastExpection이 터질 수 있는 코드**들이 더 추가가 된다면, 함수부에 달린 어노테이션은 우리가 안전하다고 **인지하지 못한 경고**까지 감출 것이다.

그러므로 **최대한 좁은 범위**로 SuppressWarnings 어노테이션을 달자!



