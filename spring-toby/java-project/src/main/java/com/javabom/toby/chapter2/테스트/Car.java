package com.javabom.toby.chapter2.테스트;

public class Car {
    private static final int MOVE_CONDITION = 4;
    private int distance;

    public Car(final int distance) {
        this.distance = distance;
    }

    public int move(String value) {
        if (isMovable(Integer.parseInt(value))) {
            distance++;
        }

        return distance;
    }

    private boolean isMovable(int value) {
        return MOVE_CONDITION < value;
    }
}
