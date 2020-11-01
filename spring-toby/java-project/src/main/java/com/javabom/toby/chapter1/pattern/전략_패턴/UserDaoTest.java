package com.javabom.toby.chapter1.pattern.전략_패턴;

/**
 * Created by jyami on 2020/11/01
 */
public class UserDaoTest {
    public static void main(String[] args) {
        /**
         * 변경되는 부분 : ConnectionManager > 인터페이스로 제공
         * Client에서 원하는 구현체를 선택해서 사용할 수 있게 한다: UserDao에서 사용할 ConnectionManager 구현 클래스를 결정한다.
         * 런타임에 오브젝트끼리 관계를 맺게 한다.
         */
        ConnectionManager dConnectionManager = new DConnectionManager();
        UserDao userDao = new UserDao(dConnectionManager);
    }
}
