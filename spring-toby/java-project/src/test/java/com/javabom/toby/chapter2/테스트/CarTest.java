package com.javabom.toby.chapter2.테스트;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @DisplayName("4 보다 큰 값일때 distance값을 증가시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"5", "6", "7"})
    void move(String value) {
        Car car = new Car(3);
        assertThat(car.move(value)).isEqualTo(4);
    }

    @DisplayName("4 이하의 값일때 distance값은 증가하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"4", "3", "2"})
    void cannotMove(String value) {
        Car car = new Car(3);
        assertThat(car.move(value)).isEqualTo(3);
    }

    @DisplayName("숫자가 아닌 값이 들어오면 Exception Throw")
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", " ", "+"})
    void exceptionThrowWhenNotNumber(String notNumber) {
        Car car = new Car(1);
        assertThatThrownBy(() -> car.move(notNumber))
                .isInstanceOf(NumberFormatException.class);
    }
}