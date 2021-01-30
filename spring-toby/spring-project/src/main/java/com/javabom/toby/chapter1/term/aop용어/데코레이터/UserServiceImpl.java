package com.javabom.toby.chapter1.term.aop용어.데코레이터;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("coreService")
public class UserServiceImpl implements UserService {

    private final List<String> users = new ArrayList<>();

    @Override
    public void addUser(String user) {
        users.add(user);
    }
}

