package Chap7_LambdaAndStream.item46;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

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
}
