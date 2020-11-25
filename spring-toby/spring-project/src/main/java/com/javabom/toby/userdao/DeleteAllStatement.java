package com.javabom.toby.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {
    @Override
    public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("DELETE FROM USERS");
    }
}
