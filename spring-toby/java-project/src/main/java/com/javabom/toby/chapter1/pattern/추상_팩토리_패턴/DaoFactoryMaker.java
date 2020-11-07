package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;

import java.util.Arrays;

public enum DaoFactoryMaker {
    USER(new UserDaoFactory(), UserDao.class), ARTICLE(new ArticleDaoFactory(), ArticleDao.class);

    DaoFactory daoFactory;
    Class<?> daoClass;

    DaoFactoryMaker(DaoFactory daoFactory, Class<?> daoClass) {
        this.daoFactory = daoFactory;
        this.daoClass = daoClass;
    }

    public static DaoFactory findByDao(Class<?> daoClass) throws ClassNotFoundException {
        return Arrays.stream(values())
                .filter(a -> a.daoClass == daoClass)
                .map(DaoFactoryMaker::getDaoFactory)
                .findAny()
                .orElseThrow(ClassNotFoundException::new);
    }

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }
}
