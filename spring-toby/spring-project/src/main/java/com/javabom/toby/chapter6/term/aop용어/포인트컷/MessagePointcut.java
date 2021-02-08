package com.javabom.toby.chapter6.term.aop용어.포인트컷;

import org.springframework.aop.support.NameMatchMethodPointcut;

public class MessagePointcut extends NameMatchMethodPointcut {
    public MessagePointcut(String methodNamePattern) {
        super();
        super.addMethodName(methodNamePattern);
    }
}
