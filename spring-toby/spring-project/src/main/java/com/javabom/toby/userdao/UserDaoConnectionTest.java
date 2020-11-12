package com.javabom.toby.userdao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoConnectionTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.printConnectionMaker();
    }
}
