package com.javabom.toby.chapter1.term.di_장점_관심사의분리;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaySystemConfiguration {

    @Bean
    public PaySystem kakaoPaySystem() {
        return new KakaoPaySystem();
    }

    @Bean
    public PaySystem paySystem() {
        return new LoggingPaySystem(kakaoPaySystem());
    }
}
