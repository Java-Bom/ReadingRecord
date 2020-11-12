package com.javabom.toby.userdao;

import com.javabom.toby.userdao.connectionmaker.ConnectionMaker;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public void setConnectionMaker(final ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void printConnectionMaker() {
        this.connectionMaker.makeConnection();
    }
}
