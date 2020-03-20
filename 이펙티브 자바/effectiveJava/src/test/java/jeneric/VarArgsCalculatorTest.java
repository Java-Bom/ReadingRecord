package jeneric;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VarArgsCalculatorTest {

    @Test
    void name() {
        //given

        //when

        assertThat(VarArgsCalculator.sum(1,2,3)).isEqualTo(6);
        assertThat(VarArgsCalculator.sum(new int[]{1,2,3})).isEqualTo(6);
        //then

    }
}