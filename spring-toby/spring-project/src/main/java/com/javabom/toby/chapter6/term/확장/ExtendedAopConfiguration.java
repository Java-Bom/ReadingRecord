package com.javabom.toby.chapter6.term.확장;

import com.javabom.toby.chapter6.term.확장.model.Message;
import com.javabom.toby.chapter6.term.확장.model.NonTargetMessage;
import com.javabom.toby.chapter6.term.확장.model.TargetMessage;
import com.javabom.toby.chapter6.term.확장.어드바이저.UpperMessageAdvice;
import com.javabom.toby.chapter6.term.확장.어드바이저.MessageAdvisor;
import com.javabom.toby.chapter6.term.확장.확장포인트컷.NameMatchMessagePointcut;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ExtendedAopConfiguration {

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public NameMatchMessagePointcut nameMatchMessagePointcut() {
        NameMatchMessagePointcut nameMatchMessagePointcut = new NameMatchMessagePointcut();
        nameMatchMessagePointcut.setMappedClassName("TargetMessage");
        nameMatchMessagePointcut.addMethodName("getM*");
        return nameMatchMessagePointcut;
    }

    @Bean
    public UpperMessageAdvice upperMessageAdvice() {
        return new UpperMessageAdvice();
    }

    @Bean
    public MessageAdvisor messageAdvisor(NameMatchMessagePointcut nameMatchMessagePointcut, UpperMessageAdvice upperMessageAdvice) {
        return new MessageAdvisor(nameMatchMessagePointcut, upperMessageAdvice);
    }

    @Bean
    public Message targetMessage() {
        return new TargetMessage("hello");
    }

    @Bean
    public Message nonTargetMessage() {
        return new NonTargetMessage("hello");
    }
}
