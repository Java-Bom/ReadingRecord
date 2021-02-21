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

    /*
    UserService 는 이 설정 파일을 읽을 때(런타임)에 DefaultUserRepository 와 런타임 의존관계를 가진다.
    여기서 DefaultUserRepository를 의존 오브젝트라고한다.(실제 사용대상의 오브젝트)
    의존 관계 주입 특징 2) 런타임 시점의 의존관계는 컨테이너나 팩토리 같은 제 3의 존재가 결정한다.(여기서는 설정파일) --> 핵심

    #173 IoC/DI) userService빈은 UserRepository 빈을 DI 받는다.(IoC 컨테이너에 의해)
     */
//    @Bean
    public UserService userService() {
        /*
        의존관계 주입 특징 3) 사용할 오브젝트에 대한 레퍼런스 -> defaultUserRepository 는 외부에서 주입(생성자, Setter,,)
         */
        return new UserService(defaultUserRepository());
    }
}
