### 아이템 69 예외는 진짜 예외일 때 사용하라.



예외를 어설프게 성능 최적화 용도로 사용하지 마라.
jvm설계 자체가 구문에 대한 최적화를 해놓았다. 

아래 예제를 보자.

```java
    //예외를 이용한 loop
    public void optimizeLoop() {
        Mountain range[] = new Mountain[10];
        try {
            int i = 0;
            while (true) {
                range[i++].climb();
            }
        } catch (Exception e) {
        }
    }

    //정상적인 loop
    public void optimizeLoop2() {
        Mountain range[] = new Mountain[10];
        for (Mountain i : range) {
            i.climb();
        }
    }

    private static class Mountain {
        public void climb() {
        }
    }
```


기본적으로 **배열에 접근**하면 **경계 검사**를 한다. 또한 **일반적인 반복문**도 **경계검사**를 한다.
이 두번의 경계검사가 중복으로 느껴지므로, 예외를 통한 loop로 **일반적인 반복문**의 **경계검사**를 제거하려는 시도였다.

하지만 **잘못된 추론**이다. 여기서 나온 **표준 관용구 forEach문**은 이미 **jvm최적화**가 **완료되어 경계검사**를 시도하지 않는다.



#### 잘 설계된 API는 정상적인 흐름에서 예외를 사용하지 않는다.

```java
//=============== 
// 상태 검사 메서드와 상태 의존메서드를 제공
    public boolean hasEngine() {
        return engine != null;
    }

    public Point move() {
        return new Point(engine.power());
    }


//=================
// 상태 검사 메서드를 제공하지 않고 정상적인 값을 반환할 수 없다면 특정 값을 제공
    public Point moveValue() {
        if (engine == null) {
            return new Point(0);
        }
        return new Point(engine.power());
    }
//===============
// optional 제공
    public Optional<Point> moveOptional() {
        if (engine == null) {
            return Optional.empty();
        }
        return Optional.of(new Point(engine.power()));
    }

```

위 예제는 정상적인 흐름에서 예외를 사용하지 않고 사용할 수 있도록 api를 제공하는 방식이다.

1. **상태 검사메서드**와 **의존 메서드**를 제공하는 방식

2. 올바르지 않은 상태일때 **특정 값**을 제공
3. 올바르지 않은 상태를 대비해 **optional 제공**



위 세가지 방식으로 해당 api에서 올바르지 않은 상태일때 exception을 발생시키지 않는 방법이다.

여기서 **2,3번째 방식**은 의존메서드에 상태 검사가 **중복**으로 들어갈때 사용하거나, 멀티 스레드 환경에서 상태 검사 후 **의존메서드를 부르는 찰나**에 **원하지 않는 결과**가 발생할 수 있으므로 해당 상황에 대비하여 사용하는 것이 좋다.





### 아이템 70 복구할 수 있는 상황에는 검사 예외를, 프로그래밍 오류에는 비검사 예외를 사용하라.



**Checked Exception**을 발생시켜야 하는 상황과 **Unchecked Exception**을 발생시켜야 하는 상황은 매우 헷갈린다.



#### 검사 예외

아이템 70에서는 해당 구분을 호출하는 쪽에서 복구할 수 있는 상황에는 **Checked Exception**을 발생시키라고 소개하고있다.

해당 api를 호출하는 쪽에서 복구할 수 있는 상황임을 암시하기 위해 Checked Exception을 던지면, 사용자는 해당 exception을 어떻게든 처리해야한다.
(throw하든 catch하든)


그러므로 복구가 가능한 상황에는 검사예외로 강제 예외처리 구문을 추가하도록 한다.



> 간혹 검사예외를 catch만 하고 아무런 조치도 취하지 않는 경우가 있는데, 비추다.
>
> 굳이 꼭 무시해도 될 상황이라면 변수 이름이라도 ignored로 바꾸고 주석이라도 달아놓아야 한다.



#### 비검사 예외

비검사 예외는 전제조건을 만족시키지 못했을때 발생한다.

배열은 0~n-1까지 접근 가능하다. 그 외의 접근을 시도할 경우 전제 조건을 만족시키지 못해 예외를 발생한다.

이런 프로그래밍 오류는 비검사 예외를 사용하도록 하자.



#### 에러

보통 jvm 자원 부족, 불변식 깨짐 등에서 Error가 발생한다.

Error는 프로세스가 더이상 수행될 수 없는 상황이므로 해당 프로세스를 종료한다.

java에서 error는 관례상 상속해서 재구현하거나 하는 일은 추천하지 않는다.



#### 상태 제공 메서드

exception이 발생한 경우 어떤 **상태때문에 예외**가 발생했는지 알아야 **쉽게 복구**가 가능하다.

그래서 **상태를 확인할 수 있는 메서드**를 제공해 주면 좋다.

위 메서드를 제공하지 않는다면, **에러 메시지를 파싱**해야하는 **힘든 노동**을 해야한다...



###아이템 71 필요없는 검사 예외 사용은 피해라

