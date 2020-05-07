package Chap2_GenerateObjectAndDestroy.item1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StaticFactoryMethodTest {

    @DisplayName("Boolean은 불변클래스이다.")
    @Test
    void booleanTest(){
        Boolean boolean1 = Boolean.valueOf(true);  // = Boolean.TRUE
        Boolean boolean2 = Boolean.valueOf("true");
        Boolean boolean3 = new Boolean(true);

        assertThat(boolean1).isSameAs(boolean2);
        assertThat(boolean1).isNotSameAs(boolean3);
    }
}
