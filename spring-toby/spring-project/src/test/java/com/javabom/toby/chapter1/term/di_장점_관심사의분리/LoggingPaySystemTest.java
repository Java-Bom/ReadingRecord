package com.javabom.toby.chapter1.term.di_장점_관심사의분리;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class LoggingPaySystemTest {

    @DisplayName("관심사의 분리와 DI")
    @Test
    void test() {
        /*
         * paySystem 에 기능이 추가되었지만 원래의 kakaoPaySystem의 코드에는 변화가없고
         * 클라이언트도 그 사실을 모른다. --> 관심사의 분리
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(PaySystemConfiguration.class);
        PaySystem paySystem = context.getBean("paySystem", PaySystem.class);

        paySystem.pay();
    }

}