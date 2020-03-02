package item14.point.adater;

public class Point implements Comparable<Point> {
    protected Integer x;
    protected Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void move(Integer x, Integer y) {
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

