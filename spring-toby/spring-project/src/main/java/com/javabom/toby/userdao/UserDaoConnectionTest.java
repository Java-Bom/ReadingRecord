package com.javabom.toby.userdao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoConnectionTest {
    public static void main(String[] args) {
        /**
         * xml 설정 정보
         */
        ApplicationContext genericContext = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao genericDao = genericContext.getBean("userDao", UserDao.class);
        genericDao.printConnectionMaker();

        ApplicationContext classPathContext = new ClassPathXmlApplicationContext("classPathContext.xml", UserDao.class);
        UserDao classPathDao = classPathContext.getBean("userDao", UserDao.class);
        classPathDao.printConnectionMaker();
    }
}
