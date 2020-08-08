package Chap12_Serialization.item85.accept;

import java.io.Serializable;

public class Accept implements Serializable {
    public int value;

    public Accept(final int value) {
        this.value = value;
    }
}
