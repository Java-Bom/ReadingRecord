package com.javabom.toby.chapter1.pattern.전략_패턴;

/**
 * Created by jyami on 2020/11/01
 */
public class UserDao {

    public UserDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private final ConnectionManager connectionManager;

}
