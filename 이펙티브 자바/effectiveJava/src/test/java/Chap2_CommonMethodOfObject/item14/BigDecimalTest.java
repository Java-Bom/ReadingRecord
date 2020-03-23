package Chap2_CommonMethodOfObject.item14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BigDecimalTest {

    @DisplayName("compareTo 와 equals 가 일반 규약을 서로 다르게 구현하고 Collection 에서 사용하는 경우")
    @Test
    void test1() {
        //given
        BigDecimal bigDecimal1 = new BigDecimal("1.0");
        BigDecimal bigDecimal2 = new BigDecimal("1.00");

        Set<BigDecimal> hashSet = new HashSet<>();
        Set<BigDecimal> treeSet = new TreeSet<>();

        //when
        hashSet.add(bigDecimal1);
        hashSet.add(bigDecimal2);

        treeSet.add(bigDecimal1);
        treeSet.add(bigDecimal2);

        //then
        assertAll("compareTo의 결과가 두 BigDecimal 은 같다고 하나 equals 는 다르다고 한다. 일반 규약을 일치시키지 않았다.",
                () -> assertThat(bigDecimal1.compareTo(bigDecimal2) == 0).isTrue(),
                () -> assertThat(bigDecimal1.equals(bigDecimal2)).isFalse(),
                // HashSet 은 equals 로 동작한다.
                () -> assertThat(hashSet.size() == 2).isTrue(),
                () -> assertThat(hashSet.size() == 1).isFalse(),
                // TreeSet 은 compareTo 로 동작한다.
                () -> assertThat(treeSet.size() == 1).isTrue()
        );
    }

}
