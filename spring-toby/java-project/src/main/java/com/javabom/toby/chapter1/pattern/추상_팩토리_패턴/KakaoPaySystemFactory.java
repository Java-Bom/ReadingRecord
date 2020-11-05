package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;

import java.time.LocalDateTime;

public class KakaoPaySystemFactory extends PaySystemFactory {

    @Override
    public PaySystemConnection getConnection() {
        System.out.printf("KakaoConnection LOG: %s%n", LocalDateTime.now().toString());
        return KakaoPaySystemConnection.INSTANCE;
    }

    @Override
    public PayRequestType getRequestType() {
        return null;
    }

    static class KakaoPaySystemConnection implements PaySystemConnection {
        static final KakaoPaySystemConnection INSTANCE = new KakaoPaySystemConnection();
    }
}
