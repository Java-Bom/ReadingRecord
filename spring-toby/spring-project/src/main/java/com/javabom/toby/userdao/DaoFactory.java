package com.javabom.toby.userdao;

import com.javabom.toby.userdao.connectionmaker.ConnectionMaker;
import com.javabom.toby.userdao.connectionmaker.LocalDBConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new LocalDBConnectionMaker();
    }
}
