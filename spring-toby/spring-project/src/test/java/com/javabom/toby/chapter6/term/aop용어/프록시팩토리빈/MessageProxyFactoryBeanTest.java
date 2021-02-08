package com.javabom.toby.chapter6.term.aop용어.프록시팩토리빈;

import com.javabom.toby.chapter6.term.aop용어.AopConfiguration;
import com.javabom.toby.chapter6.term.aop용어.포인트컷.MessagePointcut;
import com.javabom.toby.chapter6.term.aop용어.프록시.DetailMessage;
import com.javabom.toby.chapter6.term.aop용어.프록시.Message;
import com.javabom.toby.chapter6.term.aop용어.어드바이스.MessageAdvice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = AopConfiguration.class)
class MessageProxyFactoryBeanTest {
    @Autowired
    private MessageProxyFactoryBean messageProxyFactoryBean;

    @DisplayName("프록시 팩토리 빈 테스트")
    @Test
    void messageFactoryBeanTest() {
        //given

        //when
        Message message = messageProxyFactoryBean.getMessage();

        //then
        assertThat(message.getText()).isEqualTo("HELLO");
        assertThat(message.getPlainText()).isEqualTo("hello");
    }
}
