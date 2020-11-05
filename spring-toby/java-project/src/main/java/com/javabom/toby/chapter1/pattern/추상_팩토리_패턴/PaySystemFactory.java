package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;

/**
 * 추상 팩토리
 * PaySystem을 정상적으로 제공하기 위해서는 Connection과 RequestType의 쌍이 맞아야한다.
 * 즉, Connection과 RequestType의 제공은 "한 곳에서" 이루어져야한다. --> PaySystemFactory의 하위 Factory
 */
public abstract class PaySystemFactory {

    public abstract PaySystemConnection getConnection();

    public abstract PayRequestType getRequestType();
}
