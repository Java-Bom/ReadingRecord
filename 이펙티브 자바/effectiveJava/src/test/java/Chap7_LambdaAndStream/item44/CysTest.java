package Chap7_LambdaAndStream.item44;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static org.assertj.core.api.Assertions.assertThat;

public class CysTest {

    @Test
    void name() {
        //given
        List<String> test = new ArrayList<>();
        test.add("a");
        test.add("a");
        //when
        Map<String, List<String>> map = test.stream().collect(groupingBy(this::alpha));
        Map<Boolean, List<String>> map2 = test.stream().collect(partitioningBy(Objects::isNull));

        //then
    }

    @Test
    @DisplayName("표준함수형 인터페이스")
    void name2() {
        //
        UnaryOperator<Integer> unaryOperator = x->x+1;
        assertThat(unaryOperator.apply(1)).isEqualTo(2);
        //
        BinaryOperator<Integer> binaryOperator = (x,y)->x+y;
        assertThat(binaryOperator.apply(1,2)).isEqualTo(3);
        //
        Predicate<Integer> predicate = x-> x==1;
        assertThat(predicate.test(1)).isTrue();
        //
        Function<Integer,String> function = (x) -> String.valueOf(x);
        assertThat(function.apply(1)).isEqualTo("1");
        //
        Supplier<Integer> supplier = ()->1;
        assertThat(supplier.get()).isEqualTo(1);
        //
        Consumer<Integer> consumer = x ->{};

    }

    private String alpha(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
