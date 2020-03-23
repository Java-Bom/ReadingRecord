package Chap3_ClassAndInterface.item17;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by jyami on 2020/02/22
 */
class BigBigIntegerTest {

    @Test
    void unReliableMethod() {
        BigInteger bigInteger = new BigBigInteger("1234");
        assertThat(bigInteger.getClass()).isNotEqualTo(BigInteger.class);
        assertThat(bigInteger.getClass()).isEqualTo(BigBigInteger.class);
    }
}
