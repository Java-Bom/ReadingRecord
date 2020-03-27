package Chap4_Generic.item30;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToStringTest {

    @DisplayName("ToString Test")
    @Test
    void toStringTest() {
        Car car = new Car();
        assertThat(ToString.identityFunction().apply(car)).isEqualTo(car.toString());
    }

    class Car {
        private String name = "this is car";

        @Override
        public String toString() {
            return "Car{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}