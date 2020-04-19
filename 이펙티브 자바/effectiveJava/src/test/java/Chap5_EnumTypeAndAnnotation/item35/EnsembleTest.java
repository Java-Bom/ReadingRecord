package Chap5_EnumTypeAndAnnotation.item35;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnsembleTest {

    @DisplayName("열거 타입의 ordinal 메소드는 열거 타입 상수의 선언 위치를 반환한다.")
    @Test
    void name() {
        assertThat(Ensemble.TRIO.ordinal()).isEqualTo(2);
    }
}