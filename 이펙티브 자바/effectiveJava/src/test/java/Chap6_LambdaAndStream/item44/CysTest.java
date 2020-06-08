package Chap6_LambdaAndStream.item44;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

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

    private String alpha(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
