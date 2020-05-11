package Chap3_CommonMethodOfObject.item14.point.adater;

import Chap3_CommonMethodOfObject.item14.point.view.Point;

/**
 * 추상화의 이점은 포기하고 view 메소드(asPoint)를 제공하여 compareTo의 일반 규약을 지킬 수 있다.
 */
class ColorPoint implements Comparable<ColorPoint> {
    private Point point;
    private int color;

    public ColorPoint(Chap3_CommonMethodOfObject.item14.point.view.Point point, int color) {
        this.point = point;
        this.color = color;
    }

    public Point asPoint() {
        return point;
    }

    @Override
    public int compareTo(ColorPoint colorPoint) {
        int result = point.compareTo(colorPoint.point);
        if (result == 0) {
            return Integer.compare(color, colorPoint.color);
        }
        return result;
    }


}
