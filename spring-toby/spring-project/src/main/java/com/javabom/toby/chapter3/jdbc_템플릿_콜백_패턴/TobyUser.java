package com.javabom.toby.chapter3.jdbc_템플릿_콜백_패턴;

import lombok.Getter;

@Getter
public class TobyUser {
    private String id;
    private String name;
    private String password;

    public TobyUser(final String id, final String name, final String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
