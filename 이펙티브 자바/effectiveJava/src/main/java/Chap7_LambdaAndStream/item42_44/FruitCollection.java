package Chap7_LambdaAndStream.item42_44;

import java.io.Serializable;
import java.util.Comparator;

public class FruitCollection {
    private final FruitComparator comparator;

    public FruitCollection(FruitComparator comparator) {
        this.comparator = comparator;
    }

    static class FruitComparator implements Comparator<Fruit>, Serializable {
        private static final long serialVersionUID = 1;

        @Override
        public int compare(Fruit o1, Fruit o2) {
            return 0;
        }
    }
}
