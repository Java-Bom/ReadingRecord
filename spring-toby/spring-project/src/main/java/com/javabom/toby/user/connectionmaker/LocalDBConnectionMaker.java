package com.javabom.toby.user.connectionmaker;

public class LocalDBConnectionMaker implements ConnectionMaker {
    public LocalDBConnectionMaker() {
        System.out.println("LocalDB created");
    }

    @Override
    public void makeConnection() {
        System.out.println("LocalDBConnection make");
    }
}
