package com.javabom.toby.chapter1.term.IoC_용어정리;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChapterOneSpringTest {

    @DisplayName("애플리케이션 컨텍스트에서 이름으로 빈을 꺼낸다")
    @Test
    void test() {
        /*
         * 빈 팩토리, 애플리케이션 컨텍스트, IoC 컨테이너) 설정정보 기반으로 빈을 관리하는 오브젝트
         */
        // 애너테이션 설정정보(@Configuration) 기반의 애플리케이션 컨텍스트를 가져온다
        ApplicationContext context = new AnnotationConfigApplicationContext(ChapterOneConfiguration.class);

        // Constructor logging
        System.out.println("=========================");
        /*
         * 애플리케이션 컨텍스트의 장점 1) 클라이언트는 구체적인 팩토리 클래스를 알 필요가 없다.
         * 이름이나 타입만으로 원하는 빈을 가져올 수 있다.
         * 애플리케이션 컨텍스트의 장점 3) 빈을 이름으로 검색할 수도, 타입으로 검색할 수도 있다. 애너테이션으로도 (@Service, @Repository, @Component,..)
         */
        /*
        스프링 IoC 의 특징은 의존관계 검색이라는 것도 있음.
        getBean을 통해 직접 Factory에서 검색한다.(중요한건 어떤 클래스를 이용할지 결정하진 않는다는 것)

        #173 DL) 스프링이 제공하는 DL 기능은 getBean을 통해 빈을 검색하는 기능이다. 빈의 이름, 타입등으로 검색할 수 있다. 생성자나 Setter를 사용한
        의존 주입을 하지 않을 때, 또는 DI 받으려는 객체가 빈이 아닐 때 활용할 수 있다.(DI를 받기 위해서는 자기 자신도 빈이어야 하기 때문)
         */
        UserRepository userRepository = context.getBean("defaultUserRepository", UserRepository.class);
        assertThatThrownBy(() -> context.getBean(DefaultUserRepository.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class); // 타입으로

        userRepository.save(new User());
    }

    @DisplayName("애플리케이션 컨텍스트에서 타입으로 빈을 꺼낼때 중복이면 Exception")
    @Test
    void test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ChapterOneConfiguration.class);
        assertThatThrownBy(() -> context.getBean(DefaultUserRepository.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

}