package Chat4_Generic.generic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class GenericTest2 {

    @Test
    @DisplayName("한정적 와일드카드 타입")
    void name2() {
        //given

        //when
        Set<Integer> num1s = new HashSet<>();
        Set<Long> num2s = new HashSet<>();
        Set<String> num3s = new HashSet<>();

        count(num1s,num2s);
        //count(num1s,num3s);
        count2(num1s,num1s);
        //count2(num1s,num2s);

    }

    private void count(Set<? extends Number> source, Set<? extends Number> destination) {
    }

    private <E extends Number> void count2(Set<E> source,Set<E> destination) {
    }
}
