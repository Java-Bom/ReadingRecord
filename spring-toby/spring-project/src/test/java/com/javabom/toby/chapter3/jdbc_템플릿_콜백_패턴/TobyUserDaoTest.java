package com.javabom.toby.chapter3.jdbc_템플릿_콜백_패턴;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration("/applicationContext.xml")
class TobyUserDaoTest {
    @Autowired
    Chapter3UserDao chapter3UserDao;

    @DisplayName("유저 저장테스트")
    @Test
    void add() {
        /*
         * 네거티브 테스트
         * 예외상항에 대한 테스트
         */
        chapter3UserDao.add(null);
        chapter3UserDao.get("unknown");
    }

}