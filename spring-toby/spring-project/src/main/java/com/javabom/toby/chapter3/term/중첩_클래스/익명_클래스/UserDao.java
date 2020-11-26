package com.javabom.toby.chapter3.term.중첩_클래스.익명_클래스;

import com.javabom.toby.chapter3.term.중첩_클래스.JdbcContext;

import java.sql.SQLException;

public class UserDao {
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void deleteAll() throws SQLException {
        jdbcContext.workWithStatementStrategy(
                connection -> connection.prepareStatement("DELETE FROM USERS")
        );
    }

}
