# 아이템[14] - Comparable을 구현할지 고려하라

Comaparable을 구현하면 compareTo를 재정의 하여 제네릭 알고리즘과 통해 손쉽게 컬렉션을 정렬할 수 있다.  
따라서 알파벳, 숫자, 연대와 같이 순서가 명확한 클래스를 작성한다면 반드시 Comparable을 구현하도록 하자.

> Comparable은 compareTo만 가지고 있어 Functional Interface로 착각할 수 있지만 그렇지 않다.  
> [Functional Interface은 람다식에 적합한 구조여야 하기 때문이다.](https://stackoverflow.com/questions/25222575/should-comparablet-be-a-functional-interface)  

### compareTo 메서드의 일반 규약

앞장에서 살펴본 equals의 규약과 비슷하다.

먼저 짚고 넘어갈 compareTo의 반환값에 대해 살펴보면  
기준이 되는 객체를 x, 비교를 하는 객체를 y라 할 때  
x 가 y보다 작은 경우 -1을, 같을 경우 0을, 클 경우 1을 반환한다.

- 첫 번째 규약, 
  `(x.compareTo(y) < 0)` 이라면 `(y.compareTo(x) > 0)` 이다.  
  따라서 `x.compareTo(y)`가 Exception을 발생시킨다면 `y.compareTo(x)` 또한 Exception을 발생시켜야 한다.

- 두 번째 규약,  
  `x.compareTo(y) < 0` 이고 `y.compareTo(z) < 0` 이라면 `x.compareTo(z) < 0` 이다.  
  삼단 논법과 같다.
- 세 번째 규약,  
  크기가 같은 객체들끼리는 어떤 객체와 비교하더라도 항상 같아야한다.  
  `x.compareTo(y) == 0` 으로 인해 동치가 두 객체의 크기가 같다면,  
  `x.compareTo(z)` 와 `y.compareTo(z)` 는 같아야한다.
- 별도로 equals 메서도와 일관되게 작성하는 것이 좋다.  
  만약 일관되지 않게 구현을 했다면 그렇다는 것을 명시해 주도록 하자.  
  정렬된 컬렉션의 경우 equals가 아닌 compareTo를 이용하여 정렬을 시도한다.

### 구현

compareTo를 구현 할 때 타입이 다른 객체가 오는것을 신경쓰지 않아도 된다.  
단순히 다른 타입이 온다면 ClassCastException을 발생시키면 되고 대부분 그렇게 되어있다.  
다른 타입간의 비교도 허용하지만 공통 인터페이스를 기반으로 했다는 전제하에 가능하다.

compareTo의 일반 규약을 지키지 않고 구현한 클래스를 Collection에서 사용한다면 의도하지 않은 동작을 할 수 있다.

1. 정렬된 컬렉션 TreeSet, TreeMap  
   정렬된 컬렉션의 경우 동치성 비교를 compareTo로 한다.

<img src="https://user-images.githubusercontent.com/13347548/73896128-4e70e480-48c5-11ea-9a1b-04aaeb1d4fd4.png" alt="image" style="zoom:40%;" />  
HashSet에서는 equals로 한다.

<img src="https://user-images.githubusercontent.com/13347548/73896044-f2a65b80-48c4-11ea-93c4-f48083546fa8.png" alt="image" style="zoom:50%;" />  
TreeSet에서는 compare로 한다.

```java
    @DisplayName("compareTo 와 equals 가 일반 규약을 서로 다르게 구현하고 Collection 에서 사용하는 경우")
    @Test
    void test1() {
        //given
        BigDecimal bigDecimal1 = new BigDecimal("1.0");
        BigDecimal bigDecimal2 = new BigDecimal("1.00");

        Set<BigDecimal> hashSet = new HashSet<>();
        Set<BigDecimal> treeSet = new TreeSet<>();

        //when
        hashSet.add(bigDecimal1);
        hashSet.add(bigDecimal2);

        treeSet.add(bigDecimal1);
        treeSet.add(bigDecimal2);

        //then
        assertAll("compareTo의 결과가 두 BigDecimal 은 같다고 하나 equals 는 다르다고 한다. 일반 규약을 일치시키지 않았다.",
                () -> assertThat(bigDecimal1.compareTo(bigDecimal2) == 0).isTrue(),
                () -> assertThat(bigDecimal1.equals(bigDecimal2)).isFalse(),
                // HashSet 은 equals 로 동작한다.
                () -> assertThat(hashSet.size() == 2).isTrue(),
                () -> assertThat(hashSet.size() == 1).isFalse(),
                // TreeSet 은 compareTo 로 동작한다.
                () -> assertThat(treeSet.size() == 1).isTrue()
        );
    }
```

<img src="https://user-images.githubusercontent.com/13347548/74020873-cdf1d700-49dd-11ea-8b72-4575985802e6.png" alt="image" style="zoom:50%;" />

2. 정렬 알고리즘을 사용하는 Collections, Arrays

> 올바른 비교가 이루어지지 않기 때문에 제대로 활용할 수 없다.

compareTo의 작성 요령은 equals와 비슷하나 몇가지 차이점이 있다.

1. Comparable은 제네릭 인터페이스이기 때문에 equals에서 행했던 type 확인 작업과 casting이 필요없다.
2. null을 인자로 받는다면 단순하게 NullPointerException을 발생시키면 된다.

하지만 equals 가 클래스의 멤버변수를 비교할 때 equals를 재귀호출 했던것 처럼 compareTo 또한 해당 변수에 대해 compareTo를 재귀호출하여 순서를 비교한다.

### 주의점

compareTo를 이용하여 정수 타입을 비교 할 때 관계연산자를 쓰지 말고 정적메서드인 compare를 사용하도록 하자

```java
public class MyInteger {
    
    public static class CompareMyInteger implements Comparable<CompareMyInteger> {
        private Integer integer;

        @Override
        public int compareTo(CompareMyInteger compareMyInteger) {
            return Integer.compare(integer, compareMyInteger.integer);
        }
    }

    public static class RelationalMyInteger implements Comparable<RelationalMyInteger> {
        private Integer integer;

        @Override
        public int compareTo(RelationalMyInteger relationalMyInteger) {
            if (integer == relationalMyInteger.integer) {
                return 0;
            }
            if (integer > relationalMyInteger.integer) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}

/**
 * 정수형 기본타입 필드가 관계연산자를 이용하여 비교를 하는경우 == 연산자는 에서 의도치 않은 결과가 유발될 수 있다.
 */
class MyIntegerTest {

    @DisplayName("compare 로 비교하면 NullPointerException 이 발생한다.")
    @Test
    void compareTo1() {
        CompareMyInteger compareMyInteger1 = new CompareMyInteger();
        CompareMyInteger compareMyInteger2 = new CompareMyInteger();

        assertThatThrownBy(() -> compareMyInteger1.compareTo(compareMyInteger2))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("관계연산자로 비교하면 두 객체의 비교 필드가 null 일때 의도치 않은 결과를 발생 시킬 수 있다.")
    @Test
    void compareTo2() {
        RelationalMyInteger relationalMyInteger1 = new RelationalMyInteger();
        RelationalMyInteger relationalMyInteger2 = new RelationalMyInteger();

        assertThat(relationalMyInteger1.compareTo(relationalMyInteger2)).isEqualTo(0);
    }

}
```

<img src="https://user-images.githubusercontent.com/13347548/74021010-1a3d1700-49de-11ea-9527-77e753710133.png" alt="image" style="zoom:50%;" />![image](https://user-images.githubusercontent.com/13347548/74019608-473bfa80-49db-11ea-8e69-b11bd25d97eb.png)

- 기존 클래스를 확장한 구체 클래스에 새로운 값 컴포넌트를 추가했다면 compareTo 규약을 지킬 방법이 없다.
  - 구체 클래스에서 Comparable의 제네릭 타입을 Point로 구현하려면 불필요한 타입 캐스팅이 필요해지는데 이는 잘못된 구현이다.

```java
public class Point implements Comparable<Point> {
    protected Integer x;
    protected Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point point) {
        int result = Integer.compare(x, point.x);
        if (result == 0) {
            return Integer.compare(y, point.y);
        }
        return result;
    }
}

/**
 * 기반 클래스를 상속받은 구현 클래스가 값 컴포넌트를 추가했을때 일반 규약을 지킬수 없게된다.
 * compareTo는 type casting이 불필요하다 잘못된 재정의를 구현한 예제이다.
 */
class ColorPoint extends Point implements Comparable<Point> {

    private Integer color;

    public ColorPoint(Integer x, Integer y, Integer color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public int compareTo(Point point) {
        int result = super.compareTo(point);
        if (result == 0) {
            return Integer.compare(color, ((ColorPoint) point).color); // 잘못된 구현
        }
        return result;
    }

}
```

아래 테스트 코드를 보면 잘못된 다운 캐스팅을 시도할 경우 ClassCastException이 발생한다는 것을 알 수 있다.

```java
		@DisplayName("올바른 다운 캐스팅일때는 일반 규약을 지키며 동작하는 것 처럼 보인다.")
    @Test
    void test() {
        Point point = new ColorPoint(1, 2, 4);
        ColorPoint colorPoint = new ColorPoint(1, 2, 3);

        assertThat(point.compareTo(colorPoint)).isEqualTo(1);
        assertThat(colorPoint.compareTo(point)).isEqualTo(-1);
    }

    @DisplayName("잘못된 다운 캐스팅으로 ClassCastException 이 발생한다.")
    @Test
    void test1() {
        Point point = new Point(1, 2);
        ColorPoint colorPoint = new ColorPoint(1, 2, 3);

        assertThat(point.compareTo(colorPoint)).isEqualTo(0);
        assertThatThrownBy(() -> colorPoint.compareTo(point))
                .isInstanceOf(ClassCastException.class);
    }
```

<img src="https://user-images.githubusercontent.com/13347548/74025913-5aa19280-49e8-11ea-89c4-24b952d7cea1.png" alt="image" style="zoom:50%;" />

- 상속을 이용하지 않고 기반 클래스를 멤버변수로 바꾸고 view 메서드를 제공한다면 기반 클래스인 Point의 compareTo의 일반 규약을 지킬 수 있다.

```java
public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point point) {
        int result = Integer.compare(x, point.x);
        if (result == 0) {
            return Integer.compare(y, point.y);
        }
        return result;
    }
}

/**
 * 추상화의 이점은 포기하고 view 메서드(asPoint)를 제공하여 compareTo의 일반 규약을 지킬 수 있다.
 */
class ColorPoint implements Comparable<ColorPoint> {
    private Point point;
    private int color;

    public ColorPoint(Point point, int color) {
        this.point = point;
        this.color = color;
    }

    public Point asPoint() {
        return point;
    }

    @Override
    public int compareTo(ColorPoint colorPoint) {
        int result = point.compareTo(point);
        if (result == 0) {
            return Integer.compare(color, colorPoint.color);
        }
        return result;
    }
}
```

```java
		@DisplayName("구체 클래스인 Point 의 일반 규약을 지킬 수 있다.")
    @Test
    void test() {
        Point point = new Point(1, 3);
        ColorPoint colorPoint = new ColorPoint(new Point(1, 2), 1);

        assertThat(point.compareTo(colorPoint.asPoint())).isEqualTo(1);
        assertThat(colorPoint.asPoint().compareTo(point)).isEqualTo(-1);
    }
```

<img src="https://user-images.githubusercontent.com/13347548/74020677-6a67a980-49dd-11ea-9dc2-d1bbda175882.png" alt="image" style="zoom:50%;" />

- ColorPoint에서 멤버변수를 Point로 들고 있는 것과 같이 멤버 변수가 참조 필드일 경우 해당 객체의 compareTo를 **재귀호출** 하도록 하자.
  - `point.compareTo(colorPoint.point)`

### Comparator 생성 메서드

Comparator 생성 메서드 패턴을 이용한다면 보다 읽기 쉬운 코드가 될 수 있으나 성능의 저하가 뒤따르니 유의하도록 하자.

- Comparator를 반환하는 정적 메서드인 `comparing` 과 `thenComparing` 는 2개씩 정의되어 있는데 `keyExtractor`는 비교자를 찾는 역할을, `keyComparator`는 비교 방법을 지정한다.
  - `keyExtractor` 를 지정하지 않으면 정적 메서드인 `compare`를 호출한다.
  - 비교 인자인 `keyExtractor`가 `int`, `double`, `long` 이라면 `comparingInt` , `comparingDouble`, `comparingLong` 을 사용하도록 하자.

아래 코드는 동일한 비교 결과를 반환한다.

```java
public class RawSerialCompare implements Comparable<RawSerialCompare> {

    private long height;
    private double weight;
    private int age;

    public RawSerialCompare(long height, double weight, int age) {
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    @Override
    public int compareTo(RawSerialCompare rawSerialCompare) {
        int result = Long.compare(height, rawSerialCompare.height);
        if (result == 0) {
            result = Double.compare(weight, rawSerialCompare.weight);
            if (result == 0) {
                return Integer.compare(age, rawSerialCompare.age);
            }
        }
        return result;
    }
}

public class ComparatorConstructor implements Comparable<ComparatorConstructor> {
    private static final Comparator<ComparatorConstructor> COMPARATOR =
            Comparator.comparingLong((ComparatorConstructor cc) -> cc.height)
                    .thenComparingDouble(cc -> cc.weight)
                    .thenComparingInt(cc -> cc.age);

    private long height;
    private double weight;
    private int age;

    public ComparatorConstructor(long height, double weight, int age) {
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    @Override
    public int compareTo(ComparatorConstructor comparatorConstructor) {
        return COMPARATOR.compare(this, comparatorConstructor);
    }
}
```

- 주의할 점이 있다면 Comparator 생성자 메서드에서 **타입 추론을 하지 않기 때문에 타입을 지정해줘야 한다**는 점이다.

### 값의 차를 기준으로 비교하지 말자

```java
static Compartor<Object> hashCodeOrder = new Comparator<>(){
  public int compare(Object o1, Object o2){
    return o1.hashCode() - o2.hashCode();
  }
}
```

- 위와같이 구현한다면 정수 오버플로와 부동 소수점 계산방식에 따른 오류가 발생할수 있다.
- 정적 메서드인 compare 혹은 Comparator 생성 메서드를 이용하여 비교해 주도록 하자



### hashCode의 값으로 비교하는게 올바른가?

정확하지 않지만 적절한 구현이라 볼 수 없는것 같다.  
필드간의 우선순위를 비교하여 객체의 우선순위를 비교할 수 있는 Comparable의 기능을 퇴색시키는 구현이라고 본다.

```java
public class HashObject implements Comparable<HashObject> {
    private int a;
    private int b;

    public HashObject(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashObject that = (HashObject) o;
        return a == that.a &&
                b == that.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public int compareTo(HashObject hashObject) {
        System.out.println(this.hashCode());
        System.out.println(hashObject.hashCode());

        return Integer.compare(this.hashCode(), hashObject.hashCode());
    }
}

    @DisplayName("hashCode 를 이용할 경우 필드간의 우선순위 비교가 불가능해 진다.")
    @Test
    void compareTo() {
        // 두 객체 모두 hash 값이 1024로 같다.
        HashObject hashObject1 = new HashObject(1, 32);
        HashObject hashObject2 = new HashObject(2, 1);
        assertThat(hashObject1.hashCode() == 1024).isEqualTo(hashObject2.hashCode() == 1024);
        assertThat(hashObject1.compareTo(hashObject2)).isEqualTo(0);
    }
```

<img src="https://user-images.githubusercontent.com/13347548/74025163-afdca480-49e6-11ea-9eab-ee56bee6aa0e.png" alt="image" style="zoom:50%;" />