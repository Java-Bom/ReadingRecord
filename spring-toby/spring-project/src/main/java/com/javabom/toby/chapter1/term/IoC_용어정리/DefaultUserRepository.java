package com.javabom.toby.chapter1.term.IoC_용어정리;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultUserRepository implements UserRepository {

    public DefaultUserRepository() {
        log.debug("Constructor");
        log.info("[DefaultUserRepository] Constructor!");
    }

    @Override
    public void save(User user) {
        log.debug("DefaultUserRepository Save");
    }
}
