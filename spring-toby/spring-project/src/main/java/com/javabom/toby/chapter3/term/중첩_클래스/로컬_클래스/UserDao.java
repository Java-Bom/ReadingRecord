package com.javabom.toby.chapter3.term.중첩_클래스.로컬_클래스;

import com.javabom.toby.chapter3.term.중첩_클래스.JdbcContext;
import com.javabom.toby.chapter3.term.중첩_클래스.StatementStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void deleteAll() throws SQLException {
        class DeleteAllStatement implements StatementStrategy {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                return connection.prepareStatement("DELETE FROM USERS");
            }
        }
        StatementStrategy statementStrategy = new DeleteAllStatement();
        jdbcContext.workWithStatementStrategy(statementStrategy);
    }

}
