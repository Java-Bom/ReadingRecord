package com.javabom.toby.chapter6.term.aop용어.프록시팩토리빈;

import com.javabom.toby.chapter6.term.aop용어.프록시.Message;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class MessageProxyFactoryBean {
    private final ProxyFactoryBean proxyFactoryBean;

    public MessageProxyFactoryBean(Object target) {
        this.proxyFactoryBean = new ProxyFactoryBean();
        this.proxyFactoryBean.setTarget(target);
    }

    public void addAdvice(Advice advice) {
        this.proxyFactoryBean.addAdvice(advice);
    }

    public void addAdvisor(Pointcut pointcut, Advice advice) {
        this.proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, advice));
    }

    public Message getMessage() {
        return (Message) proxyFactoryBean.getObject();
    }
}
