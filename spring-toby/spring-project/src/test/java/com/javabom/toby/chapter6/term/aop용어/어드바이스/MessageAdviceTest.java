package com.javabom.toby.chapter6.term.aop용어.어드바이스;

import com.javabom.toby.chapter6.term.aop용어.프록시.DetailMessage;
import com.javabom.toby.chapter6.term.aop용어.프록시.Message;
import com.javabom.toby.chapter6.term.aop용어.프록시팩토리빈.MessageProxyFactoryBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageAdviceTest {
    @DisplayName("어드바이스는 타깃을 직접 알고 있을 필요가 없다.")
    @Test
    void adviceTest() {
        //given
        MessageProxyFactoryBean messageProxyFactoryBean = new MessageProxyFactoryBean(new DetailMessage("hello"));
        messageProxyFactoryBean.addAdvice(new MessageAdvice());

        //when
        Message message = messageProxyFactoryBean.getMessage();

        //then
        assertThat(message.getText()).isEqualTo("HELLO");
    }
}
