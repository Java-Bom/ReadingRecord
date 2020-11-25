package com.javabom.toby.chapter1.term.스코프;

import com.javabom.toby.chapter1.term.IoC_용어정리.ChapterOneConfiguration;
import com.javabom.toby.chapter1.term.IoC_용어정리.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ScopeControllerTest {

    /*
        RequestScope은 http 요청마다 새로운 인스턴스를 만들어 반환하지만 Context에서 그냥 꺼내오면 같은 인스턴스를 반환한다.
     */
    @DisplayName("Request Scope 빈을 getBean 으로 가져오면 같은 오브젝트")
    @Test
    void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ChapterOneConfiguration.class);
        UserRepository userRepository1 = context.getBean("requestUserRepository", UserRepository.class);
        UserRepository userRepository2 = context.getBean("requestUserRepository", UserRepository.class);

        assertThat(userRepository1 == userRepository2).isTrue();
    }
}