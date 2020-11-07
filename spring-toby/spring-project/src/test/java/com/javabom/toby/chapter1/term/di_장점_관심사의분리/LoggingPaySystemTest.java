package com.javabom.toby.chapter1.term.di_장점_관심사의분리;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class LoggingPaySystemTest {

    @DisplayName("관심사의 분리와 DI")
    @Test
    void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PaySystemConfiguration.class);
        LoggingPaySystem loggingKakaoPaySystem = context.getBean("loggingKakaoPaySystem", LoggingPaySystem.class);

        loggingKakaoPaySystem.logginAndPay();
    }

}