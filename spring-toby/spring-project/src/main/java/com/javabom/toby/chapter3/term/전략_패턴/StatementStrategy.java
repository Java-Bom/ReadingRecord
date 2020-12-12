package com.javabom.toby.chapter3.term.전략_패턴;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    PreparedStatement makePreparedStatement(Connection connection) throws SQLException;
}
