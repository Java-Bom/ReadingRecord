package com.javabom.toby.userdao.connectionmaker;

import java.sql.Connection;

public class LocalDBConnectionMaker implements ConnectionMaker {
    public LocalDBConnectionMaker() {
        System.out.println("LocalDB created");
    }

    @Override
    public void makeConnection() {
        System.out.println("LocalDBConnection make");
    }
}
