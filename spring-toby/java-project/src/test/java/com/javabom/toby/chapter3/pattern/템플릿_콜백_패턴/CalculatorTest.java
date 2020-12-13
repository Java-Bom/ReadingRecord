package com.javabom.toby.chapter3.pattern.템플릿_콜백_패턴;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @DisplayName("덧셈, 곱셈, 템플릿콜백패턴")
    @Test
    void test() throws IOException {
        Calculator calculator = new Calculator();
        Integer sum = calculator.calcSum(getClass().getClassLoader().getResourceAsStream("chapter3/calcSample.txt"));
        Integer multiply = calculator.calcMultiply(getClass().getClassLoader().getResourceAsStream("chapter3/calcSample.txt"));

        assertThat(sum).isEqualTo(6);
        assertThat(multiply).isEqualTo(6);
    }

}