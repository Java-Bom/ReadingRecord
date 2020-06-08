package Chap7_LambdaAndStream.item42;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedHashMapLambdaTest {

    @Test
    @DisplayName("링크드해쉬맵에서 최유성이 아닌 가장 오래된 값은 새로운 값이 들어올때 삭제된다.")
    void name() {
        //given
        HashMap<Integer, String> testMap = new LinkedHashMap<Integer, String>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return !eldest.getValue().equals("최유성");
            }
        };

        testMap.put(1, "최유성");
        testMap.put(2, "이민형");
        assertThat(testMap.size()).isEqualTo(2);

        testMap.remove(1);
        assertThat(testMap.size()).isEqualTo(1);

        testMap.put(1, "박찬인");
        assertThat(testMap.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("한정적 타입 메서드")
    void name2() {
        //given
        LocalDate targetDate = LocalDate.now();
        Predicate<LocalDate> localDateUnaryOperator = targetDate.minusDays(1)::isAfter;
        assertThat(localDateUnaryOperator.test(targetDate)).isFalse();
    }

    @Test
    @DisplayName("로타임은 컴파일 에러가 발생한다.")
    void name3() {
        //given

        //when
//        List list = new ArrayList();
//        list.add("테스트");
//        Collections.sort(list,(s1,s2)->Integer.compare(s1.length(),s2.length()));
        //then

    }
}
