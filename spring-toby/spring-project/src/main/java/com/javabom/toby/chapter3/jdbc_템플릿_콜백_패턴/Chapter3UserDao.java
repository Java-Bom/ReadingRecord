package com.javabom.toby.chapter3.jdbc_템플릿_콜백_패턴;

import com.javabom.toby.userdao.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class Chapter3UserDao {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(User user) {
        List<User> query = jdbcTemplate.query("select* frm user", (rs, rowNum) -> new User(rs.getString(0), rs.getString(1), rs.getString(2)));

        List<User> users = jdbcTemplate.queryForList("select * from user", User.class);
        jdbcTemplate.update("INSERT INTO db_user.user VALUES (?, ?, ?)", user.getId(), user.getName(), user.getPassword());
    }
}
