package Chap2_CommonMethodOfObject.item14.compare;

public class Position implements Comparable<Position> {
    private int position;

    public Position(int position) {
        this.position = position;
    }


    @Override
    public int compareTo(Position position) {
        return position.position - this.position;
    }

    public int resversed(Position position) {
        return this.position - position.position;
    }
}
