package Chap4_Generic.item31;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jyami on 2020/03/29
 */
public class Test {
    public static <T> T maxSuper(List<? super T> lists) {
        return (T) lists.get(0);
    }

    public static <T> T maxExtends(List<? extends T> lists) {
        return lists.get(0);
    }

    @org.junit.jupiter.api.Test
    void name() {
        List<Number> listNumber = Arrays.asList(1.2,2.2,3.2);

        Object objectNumber2 = maxSuper(listNumber);
        Number number2 = maxSuper(listNumber);
        Integer intNumber2 = maxSuper(listNumber);

        Object objectNumber1 = maxExtends(listNumber);
        Number number = maxExtends(listNumber);
//        Integer intNumber1 = maxExtends(listNumber);
    }
}
