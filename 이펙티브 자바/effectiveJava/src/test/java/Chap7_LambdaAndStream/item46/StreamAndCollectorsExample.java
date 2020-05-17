package Chap7_LambdaAndStream.item46;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamAndCollectorsExample {

    @DisplayName("comparing메서드")
    @Test
    void comparing_test() {
        Map<String, Long> freq = new HashMap<>();
        freq.put("a", 3L);
        freq.put("b", 4L);
        freq.put("c", 5L);

        List<String> collect = freq.keySet().stream()
                .sorted(comparing(freq::get).reversed())
                .limit(2)
                .collect(toList());

        assertThat(collect).isEqualTo(Arrays.asList("c", "b"));
    }

    @DisplayName("맵수집기 - 각 원소당 하나의 키")
    @Test
    void toMap_Test() {
        Map<String, Operation> expectedMap = new HashMap<>();
        expectedMap.put("PLUS", Operation.PLUS);
        expectedMap.put("MINUS", Operation.MINUS);
        expectedMap.put("DIVIDE", Operation.DIVIDE);

        Map<String, Operation> collect = Stream.of(Operation.values())
                .collect(toMap(Objects::toString, e -> e));

//        Map<String, Operation> failCollect = Stream.of(Operation.values())
//                .collect(toMap(e -> "SameKey", e -> e));  // java.lang.IllegalStateException: Duplicate key SameKey (attempted merging values PLUS and MINUS)

        assertThat(collect).isEqualTo(expectedMap);

    }

    @DisplayName("맵수집기 - 인수가 세개")
    @Test
    void toMap_Test_Merge() {
        Map<String, Operation> expectedMap = new HashMap<>();
        expectedMap.put("SameKey", Operation.PLUS);

        Map<String, Operation> collect = Stream.of(Operation.values())
                .collect(toMap(e -> "SameKey", e -> e, (a, b) -> a));

        assertThat(collect).isEqualTo(expectedMap);

    }

    @DisplayName("맵수집기 - 인수가 네개")
    @Test
    void toMap_Test_Four() {
        Map<Operation, String> expectedMap = new EnumMap<>(Operation.class);
        expectedMap.put(Operation.PLUS, "PLUS");
        expectedMap.put(Operation.MINUS, "MINUS");
        expectedMap.put(Operation.DIVIDE, "DIVIDE");

        EnumMap<Operation, String> collect = Stream.of(Operation.values())
                .collect(toMap(e -> e, Object::toString, (a, b) -> a, () -> new EnumMap<>(Operation.class)));

        assertThat(collect).isEqualTo(expectedMap);

    }

    @DisplayName("groupBy 의 기본쓰임")
    @Test
    void groupByBasic() {
        //given
//        List<String> dictionary = Arrays.asList("apple", "apartment", "banana", "bigbang", "count", "cleancode");
//        Map<Character, List<String>> expectedDictionary = Map.of('a', Arrays.asList("apple", "apartment"),
//                'b', Arrays.asList("banana", "bigbang"), 'c', Arrays.asList("count", "cleancode"));
//
//        //when
//        Map<Character, List<String>> collect = dictionary.stream()
//                .collect(groupingBy(word -> word.toLowerCase().charAt(0)));
//
//        //then
//        assertThat(collect).isEqualTo(expectedDictionary);
    }

    @DisplayName("groupby + downstream")
    @Test
    public void groupByDownStream() {
        //given
        List<String> dictionary = Arrays.asList("apple", "apartment", "banana", "bigbang", "count", "cleancode");
//        Map<Character, Long> expectedDictionary = Map.of('a', 2L, 'b', 2L, 'c', 2L);
//
//        //when
//        Map<Character, Long> collect = dictionary.stream()
//                .collect(groupingBy(word -> word.toLowerCase().charAt(0), counting()));
//
//        //then
//        assertThat(collect).isEqualTo(expectedDictionary);
    }

    enum Operation {
        PLUS, MINUS, DIVIDE
    }
}
