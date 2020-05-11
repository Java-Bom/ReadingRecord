# 아이템 24 멤버 클래스는 되도록 static으로 만들라

중첩 클래스는 자신을 감싼 바깥 클래스에서만 쓰여야 한다.  
그 외의 쓰임새가 있다면 톱레벨 클래스로 만들어야 한다.

중첩 클래스의 종류는 다음과 같다.

- 정적 멤버 클래스
- (비정적) 멤버 클래스
- 익명 클래스
- 지역 클래스

이중 정적 멤버 클래스를 제외한 클래스를 내부 클래스(inner class)라고 부른다.

### 정적 멤버 클래스

정적 멤버 클래스는 바깥 클래스의 private 멤버에도 접근할 수 있는 점만이 일반클래스와 다른점이다.

정적 멤버 클래스는 바깥 클래스와 함께 쓰이는 public 도우미 클래스로 쓰인다.

```java
public class Calculator {

    public enum Operator {
        PLUS("+", (x, y) -> x + y),
        MINUS("-", (x, y) -> x - y);

        private final String token;
        private final Strategy strategy;

        Operator(String token, Strategy strategy) {
            this.token = token;
            this.strategy = strategy;
        }

        public double operate(double x, double y) {
            return this.strategy.operate(x, y);
        }

        private interface Strategy {
            double operate(double x, double y);
        }
    }

}
```

#### 정적 멤버 클래스와 접근제어자

private 정적 멤버 클래스는 바깥 클래스가 표현하는 객체의 한 부분(구성요소, *멤버변수를 의미하는 것 같다.)을 나타낼 때 쓴다.

public 이거나 protected 라면 정적으로 만들지 아닐지에 대해 신중해야 한다.  
이미 릴리즈된 비정적 클래스를 추후 버전에서 정적으로 만들게 된다면 하위 호환성이 깨지게 된다.

### 비정적 멤버 클래스

구문상의 차이는 `static`이 붙어 있는가의 차이지만, 의미상의 차이는 비정적 멤버 클래스의 인스턴스가 바깥 클래스의 인스턴스와 연결된다는 점에 있다.  
그래서 다음과 같이 정규화된 `this`를 이용하여 바깥 인스턴스의 인스턴스 메서드를 호출 할 수 있다.

```java
public class NestedNonStaticExample {

    private final String name;

    public NestedNonStaticExample(String name) {
        this.name = name;
    }

    public String getName() {
        // 비정적 멤버 클래스와 바깥 클래스의 관계가 확립되는 부분
        NonStaticClass nonStaticClass = new NonStaticClass("nonStatic : ");
        return nonStaticClass.getNameWithOuter();
    }

    private class NonStaticClass {
        private final String nonStaticName;

        public NonStaticClass(String nonStaticName) {
            this.nonStaticName = nonStaticName;
        }

        public String getNameWithOuter() {
            // 정규화된 this 를 이용해서 바깥 클래스의 인스턴스 메서드를 사용할 수 있다.
            return nonStaticName + NestedNonStaticExample.this.getName();
        }
    }
}
```

만약 개념상 이러한 중첩 클래스(nested class)가 바깥 인스턴스와 독립적으로 존재할 수 있다면 정적으로 만들어 주는것이 옳다.  
바깥 인스턴스없이 비정적 멤버 클래스를 생성 할 수 없기때문이다.

대게 비정적 멤버 클래스는 바깥 클래스의 인스턴스 메소드에서 인스턴스화 되며 그 관계가 확립이 된다.  
드물게 `바깥 클래스의 인스턴스.new 비정적클래스`를 통해 생성하기도 하나, 이는 비용과 생성시간 측면에서 좋지 못하다.

```java
public class NestedNonStaticExample {

    private final String name;

    public NestedNonStaticExample(String name) {
        this.name = name;
    }

    ...

    public class NonStaticPublicClass{

    }
}
```

위와 같은 경우

```java
NestedNonStaticExample nestedNonStaticExample = new NestedNonStaticExample("name");
nestedNonStaticExample.new NonStaticPublicClass();
```

이렇게 생성할 수 있지만 되도록 피하도록 하자.

비정적 멤버 클래스는 어댑터의 역할을 정의할 때 자주 쓰인다.  
`Map`의 `keySet` 과 같은 뷰의 역할을 하는 인스턴스처럼 보이게 하는 것처럼!

<img src="https://user-images.githubusercontent.com/13347548/75698758-9e8b6d00-5cf2-11ea-9ffd-3e0b10b687c4.png" alt="image" style="zoom:50%;" />

#### 결론

**멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면 무조건 static을 붙여서 정적 멤버 클래스로 만들자.**

`static` 을 생략하면 바깥 인스턴스로의 숨은 외부 참조를 갖게 되고 심각한 경우 GC가 바깥 인스턴스를 수거하지 못하는 경우가 발생한다. (메모리 누수)

### 익명 클래스

익명 클래스는 바깥 클래스의 멤버가 아니다.  
왜냐하면 사용되는 시점에 인스턴스화 되고, 어디서든 만들 수 있기 때문이다.

또한 익명 클래스는 비정적인 문맥에서만 사용될 때 바깥 클래스의 인스턴스를 참조할 수 있다.

```java
public class AnonymousExample {
    private double x;
    private double y;


    public double operate() {
        Operator operator = new Operator() {
            @Override
            public double plus() {
                System.out.printf("%f + %f = %f\n", x, y, x + y);
                return x + y;
            }

            @Override
            public double minus() {
                System.out.printf("%f - %f = %f\n", x, y, x - y);
                return x - y;
            }
        };
        
        return operator.plus();
    }
}

interface Operator {
    double plus();

    double minus();
}
```

그리고 익명 클래스는 상수 정적 변수(`static final`)외에는 정적 변수를 가질 수 없다.

#### 제약

- 선언한 지점에서만 인스턴스를 만들수 있다.
- instanceof 검사, 클래스의 이름이 필요한 작업을 수행할 수 없다.
- 여러 인터페이스를 구현할 수 없다.
  - 구현한 익명클래스는 다른 클래스를 상속할 수 없다.
- 익명 클래스를 사용하는 클라이언트는 사용하는 익명 클래스가 상위 타입에서 상속한 멤버 외에는 호출할 수 없다.
  - 모르겠다. 상위 타입에서 상속한 멤버 외에 해당하는 부분이 어떠한 것인지.
- 표현식 중간에 등장하기 때문에 10줄이 넘어가면 가독성이 나빠진다.

#### 사용

- 람다(자바7)등장 이전에는 작은 함수 객체나 처리객체 구현에 사용되었다.
  - 람다를 쓰자.
- 정적 팩토리 메서드를 구현할 때 사용되기도 한다.

### 지역 클래스

지역 클래스는 지역변수를 선언할 수 있는 곳이라면 어디서든 선언할 수 있다.  
그에 따라 유효 범위도 지역변수와 같다.

다른 중첩 클래스들의 공통점을 하나씩 가지고 있는데

- 멤버 클래스처럼 이름을 가질 수 있고 반복해서 사용할 수 있다.
- 비정적 문맥에서 사용될 때만 바깥 인스턴스를 참조할 수 있다.
- 정적 멤버는 가질 수 없으며, 가독성을 위해 짧게(10줄이하)로 작성되어야 한다.

```java
public class LocalExample {
    private int number;

    public LocalExample(int number) {
        this.number = number;
    }

    public void foo() {
        // 지역변수처럼 선언해서 사용할 수 있다.
        class LocalClass {
            private String name;

            public LocalClass(String name) {
                this.name = name;
            }

            public void print() {
                // 비정적 문맥에선 바깥 인스턴스를 참조 할 수 있다.
                System.out.println(number + name);
            }

        }

        LocalClass localClass = new LocalClass("local");

        localClass.print();
    }
}
```

중첩 클래스가 한 메서드 안에서만 사용되며 해당 인스턴스를 생성하는 지점이 단 한곳이고 해당 타입으로 쓰기에 적합한 클래스나 인터페이스가 있다면 익명클래스로 만들고, 아니면 지역 클래스로 만들자.

