package com.javabom.toby.chapter6.term.aop용어;

import com.javabom.toby.chapter6.term.aop용어.AopConfiguration;
import com.javabom.toby.chapter6.term.aop용어.데코레이터.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AopConfiguration.class)
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void name() {
        //given

        //when
        userService.addUser("test");
        //then

    }
}
