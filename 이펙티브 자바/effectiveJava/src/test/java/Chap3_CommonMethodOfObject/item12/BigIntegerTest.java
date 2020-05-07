package Chap3_CommonMethodOfObject.item12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
public class BigIntegerTest {

    @Test
    @DisplayName("BigInteger <-> toString")
    void name() {
        BigInteger bigInteger = new BigInteger("45");
        String toString = bigInteger.toString();
        BigInteger testBigInteger = new BigInteger(toString);
        assertThat(bigInteger).isEqualTo(testBigInteger);
    }
}
