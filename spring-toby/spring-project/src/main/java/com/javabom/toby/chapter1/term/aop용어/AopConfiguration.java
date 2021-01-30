package com.javabom.toby.chapter1.term.aop용어;

import com.javabom.toby.chapter1.term.aop용어.팩토리빈.MessageFactoryBean;
import com.javabom.toby.chapter1.term.aop용어.프록시.DetailMessage;
import com.javabom.toby.chapter1.term.aop용어.프록시.Message;
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
}
