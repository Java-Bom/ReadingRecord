# 아이템 25 톱레벨 클래스는 한 파일에 하나만 담으라

두 클래스를 한 파일에 정의하지마라.

![image](https://user-images.githubusercontent.com/13347548/75746762-9ec15200-5d5e-11ea-872f-c0b73dedc39c.png)

위와 같이 이름이 중복되는 경우 컴파일 에러가 발생하게 된다!  

운이 좋아 서로 다른 패키지에 위치 한다 하여 컴파일 에러가 발생하지 않더라도 컴파일 순서에 따라 예상치 못한 결과가 출력될 수도 있다.

### 해결책

1. 톱레벨 클래스들을 서로 다른 소스 파일로 분리한다.
2. 한 파일에 담고 싶다면 정적 멤버 클래스를 사용한다.
   - 단, 다른 클래스에 딸린 부차적인 클래스여야 한다.
   - private로 선언하여 접근 범위도 최소로 관리하자.

#### AS-IS

```java
class Utensil {
    static final String NAME = "pan";
}

class Dessert {
    static final String NAME = "cake";
}
```

#### TO-BE

```java
class Utensil {
    static final String NAME = "pan";

    private static class Dessert {
        static final String NAME = "cake";
    }

}
```

