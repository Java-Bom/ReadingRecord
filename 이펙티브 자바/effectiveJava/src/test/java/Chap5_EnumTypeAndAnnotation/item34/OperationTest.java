package Chap5_EnumTypeAndAnnotation.item34;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OperationTest {

    @DisplayName("상수별 메소드 구현은 추상 메소드를 상수에 맞게 재정의하여 사용할 수 있게한다.")
    @Test
    void name() {
        int x = 8;
        int y = 4;
        assertThat(Operation.PLUS.apply(x, y)).isEqualTo(12);
        assertThat(Operation.MINUS.apply(x, y)).isEqualTo(4);
    }

    @DisplayName("열거 타입의 valueOf 메소드는 상수 이름에 맞는 열거 타입 객체를 반환한다.")
    @Test
    void name1() {
        assertThat(Operation.valueOf("PLUS")).isEqualTo(Operation.PLUS);
    }

    @DisplayName("fromString 메소드는 연산자 기호에 맞는 열거 타입 객체를 반환한다.")
    @Test
    void name2() {
        assertThat(Operation.fromString("-").get()).isEqualTo(Operation.MINUS);
    }

    @Test
    void name3() {
        assertThat(Operation.PLUS.fromString2("*")).isEqualTo(Operation.TIMES);
    }

    @Test
    void name4() {
        assertThat(Operation.fromString("*")).isEqualTo(Operation.TIMES);
    }
}