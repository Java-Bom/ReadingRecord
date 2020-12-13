package com.javabom.toby.chapter3.jdbc_템플릿_콜백_패턴;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    PreparedStatement makePreparedStatement(Connection c) throws SQLException;
}
