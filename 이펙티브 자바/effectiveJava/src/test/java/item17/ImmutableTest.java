package item17;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by jyami on 2020/02/20
 */
class ImmutableTest {

    @Test
    void changBitElement() {
        String value = "111111111111111";
        BigInteger mob = new BigInteger(value);
        BigInteger bigInteger = mob.flipBit(0);


        BitSet mob2 = new BitSet(11111);
        mob2.flip(0);

        String s = "q3";
        Stack stack = new Stack();

    }

    @Test
    void propertiesAndHashTable() throws IOException {

        Properties properties = new Properties();
        String hello = properties.getProperty("hello"); // properties의 기본동작
        Object hello1 = properties.get("hello"); // Properties의 상위클래스인 HashTable에서 물려받음
        properties.load(new FileReader("src/test/resources/input.txt"));

        int hello2 =Integer.parseInt((String)properties.get("hello"));
        properties.put("hello", hello2);

    }

    //흐음,,?
    @Test
    void makeUpperC1() {
        List<String> strings = new Immutable().makeUpperCWithFunctional(Arrays.asList("c1", "a2", "b3", "4", "5"));
        assertThat(strings.get(0)).isEqualTo("c1");
    }

    @Test
    void makeUpperC2() {
        List<String> strings = new Immutable().makeUpperCWithProcedural(Arrays.asList("c1", "a2", "b3", "4", "5"));
        assertThat(strings.get(0)).isEqualTo("c1");
    }
}