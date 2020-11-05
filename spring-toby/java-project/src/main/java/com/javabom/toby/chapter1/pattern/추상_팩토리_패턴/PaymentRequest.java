package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;

import java.time.LocalDateTime;

public class PaymentRequest {
    private final PayRequestType payRequestType;
    private final long price;
    private final LocalDateTime requestTime;

    public PaymentRequest(PayRequestType payRequestType, long price, LocalDateTime requestTime) {
        this.payRequestType = payRequestType;
        this.price = price;
        this.requestTime = requestTime;
    }
}
