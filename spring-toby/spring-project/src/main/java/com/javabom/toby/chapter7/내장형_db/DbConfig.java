package com.javabom.toby.chapter7.내장형_db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DbConfig {

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
}
