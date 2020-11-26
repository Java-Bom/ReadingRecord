package com.javabom.toby.user.dao;

import com.javabom.toby.user.User;
import com.javabom.toby.user.connectionmaker.ConnectionMaker;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;
    private DataSource dataSource;
    // 인터페이스가 아닌 구체 클래스를 DI 한다.
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setConnectionMaker(final ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void printConnectionMaker() {
        this.connectionMaker.makeConnection();
    }

    public void add(User user) throws SQLException {
        jdbcContext.workWithStatementStrategy(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USERS ('id', 'name', 'password') VALUES (?, ?, ?)");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            return preparedStatement;
        });
    }

    public void get(String id) throws SQLException {
        /**
         * find user by id;
         */
    }

    public void deleteAll() throws SQLException {
        // 클라이언트(UserDao)가 오브젝트 팩토리의 책임을 지니고 DI(DeleteAllStatement)한다.
        StatementStrategy statementStrategy = new DeleteAllStatement();
        jdbcContext.workWithStatementStrategy(statementStrategy);
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
