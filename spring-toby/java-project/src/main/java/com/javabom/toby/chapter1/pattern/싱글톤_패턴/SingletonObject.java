package com.javabom.toby.chapter1.pattern.싱글톤_패턴;

import java.util.Objects;

/**
 * 싱글톤 패턴
 * 1. 지저분한 코드
 * 2. JVM 환경에 따라 실제로는 싱글톤이 아닐 수도 있음
 * 3. 상속불가, static 필드 -> 객체지향 개념을 적용할 수 없음
 * 4. Mock 인스턴스 생성 불가. 테스트 불가.
 */
public class SingletonObject {
    private static final Object LOCK = new Object();
    private static SingletonObject INSTANCE;

    private SingletonObject() {
    }

    public static SingletonObject getInstance() {
        synchronized (LOCK) {
            if (Objects.isNull(INSTANCE)) {
                INSTANCE = new SingletonObject();
            }
        }

        return INSTANCE;
    }
}
