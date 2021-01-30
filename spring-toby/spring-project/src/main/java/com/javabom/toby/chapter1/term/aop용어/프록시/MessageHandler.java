package com.javabom.toby.chapter1.term.aop용어.프록시;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class MessageHandler implements InvocationHandler {
    private final Message detailMessage;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String text = (String) method.invoke(detailMessage, args);
        return text.toUpperCase();
    }
}
