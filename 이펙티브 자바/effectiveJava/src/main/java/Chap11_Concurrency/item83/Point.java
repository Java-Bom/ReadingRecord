package Chap11_Concurrency.item83;

/**
 * Created by jyami on 2020/07/11
 */
public class Point {
    protected final int x, y;
    private final String name;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = makeName(); // 3. subclass의 메서드를 실행한다.
    }

    protected String makeName() {
        return "[" + x + "," + y + "]";
    }

    public final String toString() {
        return name;
    }
}

class ColorPoint extends Point {

    private final String color;

    public ColorPoint(int x, int y, String color) {
        super(x, y); // 2. Point 생성자와 연결된다.
        this.color = color; // 5. color의 수행이 너무 늦다.
    }

    protected String makeName() {
        // 4. subclass의 생성자 전에 실행된다.
        return super.makeName() + ":" + color;
    }

    public static void main(String[] args) {
        // 1. subclass의 생성자를 호출한다.
        System.out.println(new ColorPoint(4, 2, "purple"));
    }
}
