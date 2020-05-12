package Chap7_LambdaAndStream.item47;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SubLists {

    public static <E> Stream<List<E>> of(List<E> list) {
        return Stream.concat(Stream.of(Collections.emptyList()), // EMPTY_LIST로 하면 unchecked, 형변환까지 해주는 emptyList() 쓰자
                prefixes(list)
                        .flatMap(SubLists::suffixes));
    }

    // (a, b, c)
    public static <E> Stream<List<E>> prefixes(List<E> list) {
        return IntStream.rangeClosed(1, list.size()) // list.size() 포함
                .mapToObj(end -> list.subList(0, end)); // (a) (a, b) (a, b, c)
    }

    // (a, b, c)
    public static <E> Stream<List<E>> suffixes(List<E> list) {
        return IntStream.rangeClosed(0, list.size()) // list.size() 포함
                .mapToObj(start -> list.subList(start, list.size())); // (a,b,c) (b,c) (c)
    }

    @Test
    void test() {
        of(Arrays.asList("a", "b", "c"));
    }
}
