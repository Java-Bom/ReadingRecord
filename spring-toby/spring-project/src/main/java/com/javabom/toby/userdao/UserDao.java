package com.javabom.toby.userdao;

import com.javabom.toby.userdao.connectionmaker.ConnectionMaker;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;
    private DataSource dataSource;

    public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setConnectionMaker(final ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void printConnectionMaker() {
        this.connectionMaker.makeConnection();
    }

    public void add(User user) throws SQLException {
        /**
         * add user
         */
    }

    public void get(String id) throws SQLException {
        /**
         * find user by id;
         */
    }

    public void deleteAll() throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement ps = connection.prepareStatement("DELETE FROM USERS");
        ps.executeUpdate();

        ps.close();
        connection.close();
    }
}
