package Chap7_LambdaAndStream.item45;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.function.Function;

class AnagramsTest {

    @Test
    void anagrams() {
//        Map<String, Set<String>> groups = new HashMap<>();
//        groups.computeIfAbsent("abc", );
        Function<String, Object> function = (unused) -> new HashMap<>().put("abc", "abc");
        Object abc = function.apply("abc");
        System.out.println(abc);

    }

}
