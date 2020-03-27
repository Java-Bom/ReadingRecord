package Chap4_Generic.item30;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenericMethod {

    public static <E> List<E> union(List<E> list1, List<E> list2) {
        List<E> allList = new ArrayList<>();
        allList.addAll(list1);
        allList.addAll(list2);
        return allList;
    }

    public void generic() {
        List<String> str = Arrays.asList("a", "b", "c");
        str.sort(Collections.reverseOrder());
        str.forEach(System.out::println);
    }
}
