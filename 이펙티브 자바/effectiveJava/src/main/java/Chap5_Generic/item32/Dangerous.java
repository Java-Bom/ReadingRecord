package Chap5_Generic.item32;

import java.util.Collections;
import java.util.List;

/**
 * Created by jyami on 2020/04/05
 */
public class Dangerous {
    // Mixing generics and varargs can violate type safety!
    static void dangerous(List<String>... stringLists) {
        List<Integer> intList = Collections.singletonList(42);
        Object[] objects = stringLists;
        objects[0] = intList; // Heap pollution
        String s = stringLists[0].get(0); // ClassCastException
    }

    public static void main(String[] args) {
        dangerous(Collections.singletonList("There be dragons!"));
    }
}
