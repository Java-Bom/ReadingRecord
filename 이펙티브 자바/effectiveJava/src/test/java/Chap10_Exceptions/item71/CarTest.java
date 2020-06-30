package Chap10_Exceptions.item71;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

    @Test
    @DisplayName("상태검사,상태의존적 메서드")
    void name() {
        //given
        Car car = new Car();
        car.addEngine(new Engine(1));
        //when
        if (car.hasEngine()) {
            Point point = car.move();
            //then
            assertThat(point.getX()).isEqualTo(1);
        }

    }

    @Test
    @DisplayName("옵셔널 반환")
    void name2() {
        //given
        Car car = new Car();
        //when
        Optional<Point> point = car.moveOptional();
        //then
        assertThat(point.isPresent()).isFalse();
    }


    @Test
    @DisplayName("특정값 반환")
    void name3() {
        //given
        Car car = new Car();
        //when
        Point point = car.moveValue();
        //then
        assertThat(point.getX()).isEqualTo(0);
    }

}