package Chap7_LambdaAndStream.item47;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StreamMappingTest {

    @DisplayName("간단한 매핑 예제")
    @Test
    void mapping() {
        List<String> words = Arrays.asList("Java", "Bom", "Study");
        List<Integer> lengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
    }

    /**
     * Q. 주어진 스트링리스트의 각 요소 중 고유문자만 뽑아내서 하나의 리스트로 반환하기
     */
    @DisplayName("잘못된 map의 사용")
    @Test
    void wrongCase() {
        //given
        List<String> wrongCase = Arrays.asList("HELLO", "WORLD");

        //when
        List<String[]> collect = wrongCase.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        //then // 하나도 안걸러짐
        // Why -> ["H","E", "L", "L", "O] ["W","O","R", "L", "D] 는 다르니까 distinct 로 걸러질 수 없음
        for (String[] str : collect) {
            for (int i = 0; i < str.length; i++) {
                System.out.printf("%s", str[i]);
            }
            System.out.println();
        }
    }

    @DisplayName("두번째 올바르지 않은 map의 사용")
    @Test
    void mapex2() {
        //given
        List<String> wrongCase = Arrays.asList("HELLO", "WORLD");

        //when
        List<Stream<String>> result = wrongCase.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    @DisplayName("flatMap으로 사용하여 평면화한다")
    @Test
    void flatMapTest() {
        //given
        List<String> words = Arrays.asList("HELLO", "WORLD");

        // 두개의 시퀀스를 하나로 평면화해야한다.
        //when
        List<String> goodResult = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        assertThat(goodResult).isEqualTo(Arrays.asList("H", "E", "L", "O", "W", "R", "D"));
    }

    @DisplayName("모던 자바 인 액션 퀴즈 5-2: 제곱 반환")
    @Test
    void quiz() {
        //given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        //when
        List<Double> result = numbers.stream()
                .map(number -> Math.pow(number, 2))
                .collect(Collectors.toList());

        //then
        assertThat(result).isEqualTo(Arrays.asList(1d, 4d, 9d, 16d, 25d));
    }

    @DisplayName("모던 자바 인 액션 퀴즈 5-2: 두 리스트의 모든 쌍")
    @Test
    void quiz2() {
        //given
        List<Integer> firsts = Arrays.asList(1, 2, 3);
        List<Integer> seconds = Arrays.asList(4, 5);

        //when
        List<List<Integer>> collect = firsts.stream()
                .flatMap(a ->
                        seconds.stream()
                                .map(b -> Arrays.asList(a, b)))
                .collect(Collectors.toList());

        //then
        System.out.println(collect);
    }

    @DisplayName("모던 자바 인 액션 퀴즈 5-2: 두 리스트의 모든 쌍 중 합이 3으로 나누어 떨어지는 것만")
    @Test
    void quiz3() {
        //given
        List<Integer> firsts = Arrays.asList(1, 2, 3);
        List<Integer> seconds = Arrays.asList(3, 4);

        //when
        List<List<Integer>> collect = firsts.stream()
                .flatMap(a ->
                        seconds.stream()
                                .filter(b -> (a + b) % 3 == 0)
                                .map(b -> Arrays.asList(a, b)))
                .collect(Collectors.toList());

        //then
        System.out.println(collect);
    }
}
