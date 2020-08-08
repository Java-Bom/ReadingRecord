package Chap12_Serialization.item86.readNoData;

import java.io.Serializable;

public class Sub extends Super implements Serializable {
    String version;

    public Sub(final String version) {
        this.version = version;
    }
}
