package com.javabom.toby.chapter7.내장형db;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.javabom.toby.chapter7.내장형_db.DbConfig;
import com.javabom.toby.chapter7.내장형_db.User;

@SpringBootTest(classes = DbConfig.class)
class H2DbTest {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void test() {
		List<User> results = jdbcTemplate.query("SELECT * FROM TEST_USER", (rs, rowNum) -> new User(rs.getString("id"), rs.getString("name")));

		assertAll(
			() -> assertThat(results.size()).isEqualTo(1),
			() -> assertThat(results).contains(new User("jaeyeon.seo", "서재연"))
		);
	}
}
