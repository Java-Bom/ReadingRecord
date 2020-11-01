package Chap12_Serialization.item86.readNoData;

import java.io.InvalidObjectException;
import java.io.Serializable;

public class Super implements Serializable {
    String defaultVersion = "1.0.0";

    private void readObjectNoData() throws InvalidObjectException {
        defaultVersion = "1.0.0";
    }
}
