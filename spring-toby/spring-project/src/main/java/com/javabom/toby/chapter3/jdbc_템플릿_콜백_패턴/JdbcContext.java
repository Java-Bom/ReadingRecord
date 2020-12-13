package com.javabom.toby.chapter3.jdbc_템플릿_콜백_패턴;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
    변하지 않는 템플릿
     */
    public void workWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            // 콜백 파라미터로 현재 컨텍스트의 Connection 을 넘긴다
            statementStrategy.makePreparedStatement(connection).executeUpdate();
        } catch (SQLException throwables) {


        } finally {
            connection.close();
        }

    }
}
