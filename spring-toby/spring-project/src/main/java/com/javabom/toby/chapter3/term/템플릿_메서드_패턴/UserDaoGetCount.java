package com.javabom.toby.chapter3.term.템플릿_메서드_패턴;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoGetCount extends UserDao {
    @Override
    protected PreparedStatement makeStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT COUNT(*) FROM USERS");
    }
}
