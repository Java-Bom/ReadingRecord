package Chap7_LambdaAndStream.item45;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.stream.Stream;

class JavaBomStreamTest {

    static Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime);
    }

    @DisplayName("스트림파이프라인은 지연평가된다.")
    @Test
    void stream() {
        Stream.of("2", 1, 2, "4")
                .filter(this::isEquals) // 계속 탄다
                .forEach(System.out::println);

        int a = 1;
        Stream.of("2", 1, 2, "4")
                .filter((value) -> {
//                    a = a+1;
                    return value.getClass().equals(Integer.class);
                });


//        Map<Boolean, List<Integer>> collect = Stream.of(1, 2, 3, 4, 5, 6)
//                .collect(groupingBy(word -> word % 2 == 0));


    }

    private boolean isEquals(Serializable value) {
        int a = 123;
        System.out.println("-------------");
        a = a + 123;
        System.out.println(a);
        return value.getClass().equals(Integer.class);
    }

    @Test
    void primesTest() {
        primes()
                .map(prime -> BigInteger.valueOf(2).pow(prime.intValueExact()).subtract(BigInteger.valueOf(1)))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);

    }


}
