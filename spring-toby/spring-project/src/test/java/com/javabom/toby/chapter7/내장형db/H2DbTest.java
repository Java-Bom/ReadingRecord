package com.javabom.toby.chapter7.내장형db;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.javabom.toby.chapter7.내장형_db.DbConfig;
import com.javabom.toby.chapter7.내장형_db.User;

@SpringBootTest(classes = DbConfig.class)
class H2DbTest {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	TransactionTemplate transactionTemplate;

	@Test
	void test() {
		List<User> results = jdbcTemplate.query("SELECT * FROM TEST_USER", (rs, rowNum) -> new User(rs.getString("id"), rs.getString("name")));

		assertAll(
			() -> assertThat(results.size()).isEqualTo(1),
			() -> assertThat(results).contains(new User("jaeyeon.seo", "서재연"))
		);
	}

	@DisplayName("트랜잭션")
	@Test
	void transaction() {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					updateSql("aaa", "bbb");
					updateSql("ccc", "ccc"); // exception
				}
			});
		} catch (RuntimeException ex) {
			// ignore
		}

		// aaa 조회
		List<Map<String, Object>> user = jdbcTemplate.queryForList("SELECT* FROM TEST_USER WHERE ID = 'aaa'");

		assertThat(user).isEmpty();
	}

	private void updateSql(String id, String name) {
		if (id.equals("ccc")) {
			throw new RuntimeException();
		}
		jdbcTemplate.execute(String.format("INSERT INTO TEST_USER VALUES('%s', '%s')", id, name));
	}

}
