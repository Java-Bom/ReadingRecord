package com.javabom.toby.chapter6.term.확장.어드바이저;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class MessageAdvisor extends DefaultPointcutAdvisor {
    public MessageAdvisor(Pointcut pointcut, Advice advice) {
        super(pointcut, advice);
    }
}
