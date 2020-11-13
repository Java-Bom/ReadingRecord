package com.javabom.toby.userdao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;

public class UserDaoConnectionTest {
    public static void main(String[] args) throws SQLException {
        /**
         * DaoFactory를 설정정보로 사용하는 방법
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao annotationUserDao = context.getBean("dataSourceUserDao", UserDao.class);
    }
}
