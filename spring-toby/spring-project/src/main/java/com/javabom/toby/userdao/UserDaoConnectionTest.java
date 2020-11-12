package com.javabom.toby.userdao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.net.URL;
import java.net.URLClassLoader;

public class UserDaoConnectionTest {
    public static void main(String[] args) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        for (URL url : urls) {
            System.out.println(url.getFile());
        }

        /**
         * DaoFactory를 설정정보로 사용하는 방법
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao annotationUserDao = context.getBean("userDao", UserDao.class);
        System.out.println("---------------");
        annotationUserDao.printConnectionMaker();
        /**
         * GenericXmlApplicationContext
         */

        /*
        ApplicationContext genericXmlContext = new GenericXmlApplicationContext("");
        UserDao genericUserDao = genericXmlContext.getBean("userDao", UserDao.class);
        System.out.println("---------------");
        genericUserDao.printConnectionMaker();
        */
        /**
         * ClassPathXmlApplicationContext
         */
        ApplicationContext classPathContext = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
        UserDao classPathUserDao = classPathContext.getBean("userDao", UserDao.class);
        System.out.println("---------------");
        classPathUserDao.printConnectionMaker();

    }
}
