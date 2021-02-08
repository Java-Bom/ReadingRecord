package com.javabom.toby.chapter6.term.aop용어;

import com.javabom.toby.chapter6.term.aop용어.어드바이스.MessageAdvice;
import com.javabom.toby.chapter6.term.aop용어.팩토리빈.MessageFactoryBean;
import com.javabom.toby.chapter6.term.aop용어.포인트컷.MessagePointcut;
import com.javabom.toby.chapter6.term.aop용어.프록시.DetailMessage;
import com.javabom.toby.chapter6.term.aop용어.프록시.Message;
import com.javabom.toby.chapter6.term.aop용어.프록시팩토리빈.MessageProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AopConfiguration {

    @Bean
    public MessageFactoryBean messageFactoryBean() {
        return new MessageFactoryBean(new DetailMessage("test"), Message.class);
    }

    @Bean
    public MessageAdvice messageAdvice() {
        return new MessageAdvice();
    }

    @Bean
    public MessagePointcut messagePointcut() {
        return new MessagePointcut("getT*");
    }

    @Bean
    public MessageProxyFactoryBean messageProxyFactoryBean(MessagePointcut messagePointcut, MessageAdvice messageAdvice) {
        MessageProxyFactoryBean messageProxyFactoryBean = new MessageProxyFactoryBean(new DetailMessage("hello"));
        messageProxyFactoryBean.addAdvisor(messagePointcut, messageAdvice);
        return messageProxyFactoryBean;
    }
}
