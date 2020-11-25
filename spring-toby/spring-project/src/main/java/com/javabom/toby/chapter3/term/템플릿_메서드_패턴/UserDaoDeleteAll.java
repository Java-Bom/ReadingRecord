package com.javabom.toby.chapter3.term.템플릿_메서드_패턴;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoDeleteAll extends UserDao {
    @Override
    protected PreparedStatement makeStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("DELETE FROM USERS");
    }
}
