package item14.point.extend;


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
 * compareTo는 type casting 이 불필요하다.
 * 잘못된 재정의를 구현한 예제이다.
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

