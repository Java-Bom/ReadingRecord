# 아이템39 명명 패턴보다 애너테이션을 사용하라

- 명명 패턴
  - 변수, 함수의 이름을 일관된 방식으로 작성하는 패턴
  - 단점
    - 오타나면 안된다.
    - 명명패턴을 써야하는 곳(의도한 곳)에서만 쓸거라는 보장이 없다.
    - 명명패턴을 적용한 요소를 매개변수로 전달할 방법이 마땅치 않다.

따라서 애너테이션으로 해결할 수 있는 일을 명명패턴으로 처리할 이유가 없다.

일단 자바에서 제공하는 애너테이션을 사용하고 정말 필요한 경우에 애너테이션을 직접 정의해서 사용하라.

### 마커 애너테이션

아무런 매개변수 없이 단순히 대상에 마킹하는 용도로 사용되는 애너테이션을 의미한다.

```java
/**
* 테스트 메서드임을 선언하는 애너테이션
* 매개변수 없는 정적 메서드 전용
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test{}
```

위와같이 주석으로 해당 애너테이션의 설명을 적어둘 수 있지만 주석처럼 동작을 강제 할 수 는 없기 때문에  
애너테이션 프로세서에서 해당 요구사항을 충족시켜 구현을 해야한다.

---

### 반복 가능 애너테이션

자바 8에서 여러 개의 값을 받는 애너테이션을 만들 수 있다.

`@Repeatable` 을 단 애너테이션을 반환하는 '컨테이너 애너테이션'을 정의하는것이다.  
`@Repeatable` 에 이 컨테이너 애너테이션의 class 객체를 매개변수로 전달해야한다.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTestContainer {
    ExceptionTest[] value();
}
```

컨테이너 애너테이션은 내부 애너테이션(`ExceptionTest`)타입의 배열을 반환하는 `value()` 를 정의해야 한다.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
public @interface ExceptionTest {
    Class<? extends Throwable> value();
}
```

반복 가능 애너테이션을 하나만 적용한 경우와 여러 개를 적용한 경우를 구분하기 때문에 주의해야한다.

아래와 같은 메서드가 있다고 가정하자.

```java
public class Repeatable {

    @ExceptionTest(IllegalArgumentException.class)
    @ExceptionTest(NullPointerException.class)
    public void repeatAnnotationMethod() {

    }

    @ExceptionTest(IllegalArgumentException.class)
    public void notRepeatAnnotationMethod() {

    }
}
```

반복 가능 애너테이션(`ExceptionTest`)을 여러 번 단다음 반복 가능 애너테이션이 있는지를 `isAnnotationPresent()`로 검사한다면 `false`를 반환한다.  
이 경우 Contatiner 애너테이션(`ExceptionTestContainer`)을 인식하기 때문이다.

한개만 단 다면 반대의 상황이 된다.

아래 테스트 코드로 확인해 볼 수 있다.

```java
class RepeatableTest {

    @DisplayName("두개이상, 반복가능 애너테이션이 달려있다면 Container를 인식한다.")
    @Test
    void name() throws NoSuchMethodException {
        //given
        Method repeatAnnotationMethod = Repeatable.class.getMethod("repeatAnnotationMethod");

        //when
        boolean annotationPresent = repeatAnnotationMethod.isAnnotationPresent(java.lang.annotation.Repeatable.class);
        boolean exceptionTestAnnotationPresent = repeatAnnotationMethod.isAnnotationPresent(ExceptionTest.class);
        boolean exceptionTestContainerAnnotationPresent = repeatAnnotationMethod.isAnnotationPresent(ExceptionTestContainer.class);

        //then
        assertThat(repeatAnnotationMethod).isNotNull();
        assertThat(annotationPresent).isFalse();
        assertThat(exceptionTestAnnotationPresent).isFalse();
        assertThat(exceptionTestContainerAnnotationPresent).isTrue();
    }

    @DisplayName("단 한개의 반복가능 애너테이션이 달려있다면 Container를 인식하지 않는다..")
    @Test
    void name2() throws NoSuchMethodException {
        //given
        Method notRepeatAnnotationMethod = Repeatable.class.getMethod("notRepeatAnnotationMethod");

        //when
        boolean annotationPresent = notRepeatAnnotationMethod.isAnnotationPresent(java.lang.annotation.Repeatable.class);
        boolean exceptionTestAnnotationPresent = notRepeatAnnotationMethod.isAnnotationPresent(ExceptionTest.class);
        boolean exceptionTestContainerAnnotationPresent = notRepeatAnnotationMethod.isAnnotationPresent(ExceptionTestContainer.class);

        //then
        assertThat(notRepeatAnnotationMethod).isNotNull();
        assertThat(annotationPresent).isFalse();
        assertThat(exceptionTestAnnotationPresent).isTrue();
        assertThat(exceptionTestContainerAnnotationPresent).isFalse();
    }
}
```



---

### 관련 Java-Bom 질문

[해당 예외의 클래스파일이 런타임에는 존재하지 않을 수 있다](https://github.com/Java-Bom/ReadingRecord/issues/99)

[@interface 의 value 메서드](https://github.com/Java-Bom/ReadingRecord/issues/98)

[javax.annotation.processing](https://github.com/Java-Bom/ReadingRecord/issues/97)

