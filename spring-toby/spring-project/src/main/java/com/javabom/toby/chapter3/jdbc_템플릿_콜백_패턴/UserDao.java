package com.javabom.toby.chapter3.jdbc_템플릿_콜백_패턴;

import com.javabom.toby.userdao.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(User user) {
        jdbcTemplate.update("INSERT INTO db_user.user VALUES (?, ?, ?)", user.getId(), user.getName(), user.getPassword());
    }
}
