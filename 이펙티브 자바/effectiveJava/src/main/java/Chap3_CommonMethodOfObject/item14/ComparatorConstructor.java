package Chap3_CommonMethodOfObject.item14;

import java.util.Comparator;

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
