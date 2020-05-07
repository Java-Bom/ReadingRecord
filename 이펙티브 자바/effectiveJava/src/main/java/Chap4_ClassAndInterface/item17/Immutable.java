package Chap4_ClassAndInterface.item17;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by jyami on 2020/02/20
 */
public class Immutable {
    public void testBigInteger() {
        BigInteger bigInteger = new BigInteger("123445");
        BigInteger negate = bigInteger.negate();
    }

    public List<String> makeUpperCWithProcedural(List<String> list) {
        // 기존방식 (절차적 프로그래밍)
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.startsWith("c")) {
                System.out.println(s.toUpperCase());
            }
        }
        return list;
    }

    public List<String> makeUpperCWithFunctional(List<String> list) {
        // stream API를 이용한 방식 (함수형 프로그래밍)
        list.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
        return list;
    }
}
