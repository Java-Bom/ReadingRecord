package com.javabom.toby.chapter3.jdbc_템플릿_콜백_패턴;

import java.sql.SQLException;

public class UserDaoWithTemplateCallback {
    private final JdbcContext jdbcContext;

    public UserDaoWithTemplateCallback(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void deleteAll() throws SQLException {
        this.jdbcContext.workWithStatementStrategy(
                c -> c.prepareStatement("delete from users")); // 변하는 부분을 콜백으로 뺀다
    }

}
