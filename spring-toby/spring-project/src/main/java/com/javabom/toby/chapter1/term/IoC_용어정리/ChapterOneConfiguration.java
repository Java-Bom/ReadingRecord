package com.javabom.toby.chapter1.term.IoC_용어정리;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * 애플리케이션 컨텍스트가 활용할 설정정보
 */
@Configuration
public class ChapterOneConfiguration {

    /**
     * 빈: 스프링 컨테이너가 생명주기를 제어하는 오브젝트
     */
    @Bean
    public UserRepository defaultUserRepository() {
        return new DefaultUserRepository();
    }

    @Bean
    public UserRepository defaultUserRepositoryDuplication() {
        return new DefaultUserRepository();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserRepository prototypeUserRepository() {
        return new DefaultUserRepository();
    }

    // Http 요청 올때마다 새로 만드는 Request Scope
    @Bean
    @RequestScope
    public UserRepository requestUserRepository() {
        return new DefaultUserRepository();
    }

    @Bean
    @SessionScope
    public UserRepository sessionUserRepository() {
        return new DefaultUserRepository();
    }
}
