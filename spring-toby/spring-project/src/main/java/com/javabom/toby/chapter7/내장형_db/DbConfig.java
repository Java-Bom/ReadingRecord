package com.javabom.toby.chapter7.내장형_db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class DbConfig {

	/**
	 * 내장형 db Builder 를 사용한 EmbeddedDatabase
	 * DataSource 를 상속
	 */
	@Bean
	public EmbeddedDatabase embeddedDatabase() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("setup-sql.sql")
			.build();
	}

	@Bean
	public JdbcTemplate template() {
		return new JdbcTemplate(embeddedDatabase());
	}

	/**
	 * 트랜잭션을 보장을 위한 Template
	 */
	@Bean
	public TransactionTemplate transactionTemplate() {
		return new TransactionTemplate(new DataSourceTransactionManager(embeddedDatabase()));
	}
}
