package com.javabom.toby.chapter6.term.aop용어.데코레이터;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceLogImpl implements UserService {

    private final UserService coreService;

    @Override
    public void addUser(String user) {
        System.out.println(user);
        this.coreService.addUser(user);
    }
}
