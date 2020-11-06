package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javabom.toby.chapter1.pattern.추상_팩토리_패턴.PaymentSystemFactoryMaker.NAVER;

class PaySystemFactoryTest {

    @DisplayName("추상 팩토리 패턴")
    @Test
    public void abstractFactory() {
        PaySystemFactory paySystemFactory = NAVER.getPaySystemFactory();

        /** 올바른 paySystem을 위해 PaySystemConnection PayRequestType 호환되어야하기때문에
         *  하나의 Factory에서 연관되는 두 Conneciton, RequestType을 반환하도록 구현하고
         *  그 Factory를 요청에 따라 얻을 수 있는 AbstractFactory를 둔다.
         */
        PaySystemConnection paySystemConnection = paySystemFactory.getConnection();
        PayRequestType payRequestType = paySystemFactory.getRequestType();
    }

}