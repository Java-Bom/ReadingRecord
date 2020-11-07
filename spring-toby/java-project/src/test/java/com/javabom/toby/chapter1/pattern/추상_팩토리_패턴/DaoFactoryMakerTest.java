package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;

import org.junit.jupiter.api.Test;

class DaoFactoryMakerTest {

    @Test
    void abstractPattern() throws ClassNotFoundException {
        DaoFactory articleDao = DaoFactoryMaker.findByDao(ArticleDao.class);
        DaoFactory userDao = DaoFactoryMaker.findByDao(UserDao.class);
    }

}