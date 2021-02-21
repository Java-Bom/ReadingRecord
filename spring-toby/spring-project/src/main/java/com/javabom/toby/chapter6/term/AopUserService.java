package com.javabom.toby.chapter6.term;

import org.springframework.stereotype.Service;

@Service(value = "aopUserService")
public class AopUserService implements AopTestInterface {

    public void hello() {
        System.out.println("Hello!");
    }

    public void bye() {
        System.out.println("bye~!");
    }

    @Override
    public void test() {
        System.out.println("Test");
    }

}
