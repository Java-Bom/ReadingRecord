package Chap7_LambdaAndStream.item46;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

class StreamSideEffectTest {

    @DisplayName("summing, average, summarizing")
    @Test
    void 컬렉터_1() {
        List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5, 6);

        int sum = elements.stream().collect(Collectors.summingInt(a -> a * a));

        IntSummaryStatistics collect = elements.stream().collect(Collectors.summarizingInt(a -> a + a));

        System.out.println(collect);
    }

    @DisplayName("collecting")
    @Test
    void 콜렉팅() {
        List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5, 6);

        Integer collect = elements.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collection::size));

    }

    @DisplayName("groupingBy")
    @Test
    void grouping_By() {
        List<String> dictionary = Arrays.asList("Hello", "I'm", "Java-bom");

        Map<Boolean, List<String>> result = dictionary.stream()
                .collect(groupingBy(word -> word.length() > 10));

        System.out.println(result);
    }

    @DisplayName("partitioningBy")
    @Test
    void partitioningBy_map() {
        List<String> dictionary = Arrays.asList("Hello", "I'm", "Java-bom");

        Map<Boolean, List<String>> result = dictionary.stream()
                .collect(partitioningBy(word -> word.length() > 10));

        System.out.println(result);
    }
}
