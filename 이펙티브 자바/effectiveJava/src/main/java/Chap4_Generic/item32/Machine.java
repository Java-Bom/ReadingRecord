package Chap4_Generic.item32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jyami on 2020/04/05
 */
public class Machine<T> {
    private List<T> versions = new ArrayList<>();

    public final void safe(T... toAdd) {
    }
}
