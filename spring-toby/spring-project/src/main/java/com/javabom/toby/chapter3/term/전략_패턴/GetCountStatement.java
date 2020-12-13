package com.javabom.toby.chapter3.term.전략_패턴;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetCountStatement implements StatementStrategy {
    @Override
    public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT COUNT(*) FROM USERS");
    }
}
