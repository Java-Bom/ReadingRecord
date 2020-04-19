package Chap4_Generic.item32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jyami on 2020/04/12
 */
public class PickTwo {
    static <T> T[] pickTwo(T a, T b, T c) {
        T[] ts = toArray(a, b);
        return ts;
//        switch (ThreadLocalRandom.current().nextInt(3)) {
//            case 0:
//                return toArray(a, b);
//            case 1:
//                return toArray(b, c);
//            case 2:
//                return toArray(a, c);
//        }
//        throw new AssertionError();
    }

    static <T> List<T> pickTwoList(T a, T b, T c) {
        List<T> ts = Arrays.asList(a, b);
        return ts;
//        switch (ThreadLocalRandom.current().nextInt(3)) {
//            case 0:
//                return Arrays.asList(a, b);
//            case 1:
//                return Arrays.asList(b, c);
//            case 2:
//                return Arrays.asList(a, c);
//        }
//        throw new AssertionError();
    }

    static <T> T[] toArray(T... args) {
        return args;
    }

    public static void main(String[] args) {
        pickTwoList("좋은", "빠른", "저렴한");
        List<String> strings = Arrays.asList("좋은", "빠른");
//        System.out.println(strings.get(0));
//        String[] strings = pickTwo("좋은", "빠른", "저렴한");
    }
}
