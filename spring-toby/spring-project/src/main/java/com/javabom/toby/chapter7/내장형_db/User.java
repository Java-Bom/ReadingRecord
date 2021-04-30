package com.javabom.toby.chapter7.내장형_db;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class User {
	private String id;
	private String name;

	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
