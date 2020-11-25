package com.javabom.toby.chapter1.pattern.싱글톤_패턴;

import java.util.Objects;

/**
 * 싱글톤 패턴
 * 1. 지저분한 코드
 * 2. JVM 환경에 따라 실제로는 싱글톤이 아닐 수도 있음
 * 3. 상속불가, static 필드 -> 객체지향 개념을 적용할 수 없음
 * 4. Mock 인스턴스 생성 불가. 테스트 불가.
 */
public class UserDao {
    private static final Object LOCK = new Object();
    private static UserDao INSTANCE;
    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * UserDao 객체의 책임은 User모델을 다루는 로직들로만 응집되어있어야하는데
     * 책임과 전혀 무관한 싱글톤만을 위한 코드가 들아간다.
     */
    public static UserDao getInstance(Connection connection) {
        synchronized (LOCK) {
            if (Objects.isNull(INSTANCE)) {
                INSTANCE = new UserDao(connection);
            }
        }

        return INSTANCE;
    }

    public User getUser() {
        // connection 에서 user얻어옴
        Object result = connection.execute("select * from user");
        return (User) result;
    }
}
