package Chap12_Serialization.item86.proxy;

import java.io.Serializable;

public class Sub extends Super implements Serializable {
    private int subValue;

    public Sub(final int subValue) {
        super(subValue - 1);
        this.subValue = subValue;
    }

    public int getSubValue() {
        return subValue;
    }
}
