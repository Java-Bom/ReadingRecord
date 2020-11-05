package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;

public enum PaymentSystemFactoryMaker {
    NAVER(new NaverPaySystemFactory()),
    JAVABOM(new KakaoPaySystemFactory());

    private final PaySystemFactory paySystemFactory;

    PaymentSystemFactoryMaker(PaySystemFactory paySystemFactory) {
        this.paySystemFactory = paySystemFactory;
    }

    public PaySystemFactory getPaySystemFactory() {
        return paySystemFactory;
    }
}
