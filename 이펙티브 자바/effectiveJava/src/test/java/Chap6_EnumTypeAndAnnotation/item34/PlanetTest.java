package Chap6_EnumTypeAndAnnotation.item34;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlanetTest {
    @DisplayName("열거 타입의 values 메소드는 자신의 상수들을 배열에 담아 반환한다.")
    @Test
    void name() {
        Planet[] planets = Planet.values();

        assertThat(planets.length).isEqualTo(8);
        assertThat(planets[0]).isEqualTo(Planet.MERCURY);
    }

    @DisplayName("열거 타입의 toString 메소드는 재정의 하여 사용할 수 있다.")
    @Test
    void name1() {
        assertThat(Planet.JUPITER.toString()).isEqualTo("jupiter");
    }
}
