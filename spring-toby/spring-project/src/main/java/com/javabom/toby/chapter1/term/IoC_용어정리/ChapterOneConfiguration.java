package com.javabom.toby.chapter1.term.IoC_용어정리;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
