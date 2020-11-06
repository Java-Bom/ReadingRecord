package com.javabom.toby.chapter1.term.싱글톤_용어정리;

import com.javabom.toby.chapter1.term.IoC_용어정리.ChapterOneConfiguration;
import com.javabom.toby.chapter1.term.IoC_용어정리.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


class SingletonTest {

    @DisplayName("IoC컨테이너는 싱글톤 레지스트리로서의 역할을 한다")
    @Test
    void singletonRegistry() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ChapterOneConfiguration.class);

        UserRepository user1 = context.getBean("defaultUserRepository", UserRepository.class);
        UserRepository user2 = context.getBean("defaultUserRepository", UserRepository.class);

        /*
          컨테이너에 의해 생성되는 오브젝트는 동일성을 만족한다.
          즉, 컨테이너는 빈의 생명주기를 관리함과 동시에 싱글톤 오브젝트를 반환하는 싱글톤 레지스트리로서의 역할을 한다.
         */
        assertThat(user1).isEqualTo(user2); // 동등성 (equlasTo), 동일한 정보를 담고있다.
        assertThat(user1 == user2).isTrue(); // 동일성, 완전히 동일한 오브젝트이다
    }

}