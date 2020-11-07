package com.javabom.toby.chapter1.pattern.싱글톤_패턴;

import java.util.Objects;

/**
 * 싱글톤 패턴
 * 1. 지저분한 코드
 * 2. JVM 환경에 따라 실제로는 싱글톤이 아닐 수도 있음
 * 3. 상속불가, static 필드 -> 객체지향 개념을 적용할 수 없음
 * 4. Mock 인스턴스 생성 불가. 테스트 불가.
 */
public class Calculator {
    private static final Object LOCK = new Object();
    private static Calculator INSTANCE;

    private Calculator() {
    }

    /**
     * Calculator 객체의 책임은 계산하는 것인데
     * 책임과 전혀 무관한 싱글톤만을 위한 코드가 들아간다.
     */
    public static Calculator getInstance() {
        synchronized (LOCK) {
            if (Objects.isNull(INSTANCE)) {
                INSTANCE = new Calculator();
            }
        }

        return INSTANCE;
    }

    public int add(int a, int b) {
        return a + b;
    }
}
