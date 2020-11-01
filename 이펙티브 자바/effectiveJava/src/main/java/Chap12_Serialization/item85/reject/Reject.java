package Chap12_Serialization.item85.reject;

import java.io.Serializable;

public class Reject implements Serializable {
    public int value;

    public Reject(final int value) {
        this.value = value;
    }
}
