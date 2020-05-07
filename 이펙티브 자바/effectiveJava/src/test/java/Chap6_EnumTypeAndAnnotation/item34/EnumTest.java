package Chap6_EnumTypeAndAnnotation.item34;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class EnumTest {
    public static final int MONDAY = 0;

    @DisplayName("열거 타입은 컴파일타임에 타입 안정성을 제공한다.")
    @Test
    void name() {
//        enumTypeMethod(MONDAY);
        enumTypeMethod(WeekDay.MONDAY);
    }

    private void enumTypeMethod(WeekDay weekDay) {

    }

    @DisplayName("열거 타입의 toString 메소드는 상수 이름을 문자열로 반환한다.")
    @Test
    void name1() {
        assertThat(WeekDay.MONDAY.toString()).isEqualTo("MONDAY");
    }

    @Test
    void name2() {
        Arrays.stream(WeekDay.values())
                .forEach(WeekDay::printHi);
    }
}
