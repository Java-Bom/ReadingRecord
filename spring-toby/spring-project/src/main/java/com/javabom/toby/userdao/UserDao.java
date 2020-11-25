package com.javabom.toby.userdao;

import com.javabom.toby.userdao.connectionmaker.ConnectionMaker;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("DELETE FROM USERS");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public int getCount() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();

            ps = connection.prepareStatement("SELECT COUNT(*) FROM USERS");

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
