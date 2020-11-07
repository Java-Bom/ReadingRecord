package com.javabom.toby.chapter1.term.di_장점_관심사의분리;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class LoggingPaySystem {
    private final PaySystem paySystem;

    public LoggingPaySystem(PaySystem paySystem) {
        this.paySystem = paySystem;
    }

    public void logginAndPay() {
        log.debug("{}, {}", paySystem.getClass(), LocalDateTime.now());
        paySystem.pay();
    }
}
