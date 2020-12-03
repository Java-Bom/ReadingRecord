package com.javabom.toby.chapter3.jdbc_템플릿_콜백_패턴;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class TobyUserDao {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(TobyUser user) {
        jdbcTemplate.update("INSERT INTO TOBY_USER VALUES (?, ?, ?)", user.getId(), user.getName(), user.getPassword());
    }

    public void get(final String unknown) {

    }
}
