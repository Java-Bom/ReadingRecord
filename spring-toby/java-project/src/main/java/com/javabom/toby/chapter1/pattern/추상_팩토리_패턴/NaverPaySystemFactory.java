package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;

import java.time.LocalDateTime;

/**
 * PaymentSystemFactory를 구현한 구체 팩토리클래스
 */
public class NaverPaySystemFactory extends PaySystemFactory {

    /**
     * Connection 얻는 법을 정의한다
     */
    @Override
    public PaySystemConnection getConnection() {
        System.out.printf("NaverPayConnection LOG: %s%n", LocalDateTime.now().toString());
        return NaverPayPaySystemConnection.INSTANCE;
    }

    @Override
    public PayRequestType getRequestType() {
        return PayRequestType.NAMER_PAY_REQUEST;
    }

    static class NaverPayPaySystemConnection implements PaySystemConnection {
        static final NaverPayPaySystemConnection INSTANCE = new NaverPayPaySystemConnection();
    }
}
