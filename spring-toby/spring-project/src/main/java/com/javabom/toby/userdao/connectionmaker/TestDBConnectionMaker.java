package com.javabom.toby.userdao.connectionmaker;

public class TestDBConnectionMaker implements ConnectionMaker {
    public TestDBConnectionMaker() {
        System.out.println("TestDB created");
    }

    @Override
    public void makeConnection() {
        System.out.println("TestDBConnection make");
    }
}
