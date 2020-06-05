package Chap7_LambdaAndStream.item48;

import java.math.BigInteger;
import java.util.stream.Stream;


public class Parellel {
    static void mersenne() {
        primes().map(p -> new BigInteger(String.valueOf(2)).pow(p.intValueExact()).subtract(new BigInteger(String.valueOf(1))))
                .parallel()
                .filter(mersenn -> mersenn.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);

    }

    static Stream<BigInteger> primes() { // 이 코드를 실행하면 무한으로 돌아간다.
        return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime);
    }

}
