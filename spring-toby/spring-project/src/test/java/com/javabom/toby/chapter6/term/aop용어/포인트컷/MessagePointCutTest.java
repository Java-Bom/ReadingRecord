package com.javabom.toby.chapter6.term.aop용어.포인트컷;

import com.javabom.toby.chapter6.term.aop용어.어드바이스.MessageAdvice;
import com.javabom.toby.chapter6.term.aop용어.프록시.DetailMessage;
import com.javabom.toby.chapter6.term.aop용어.프록시.Message;
import com.javabom.toby.chapter6.term.aop용어.프록시팩토리빈.MessageProxyFactoryBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessagePointCutTest {
    @DisplayName("포인트컷은 어드바이스와 함께 묶어서 어드바이저로 적용한다.")
    @Test
    void pointcutTest() {
        //given
        MessageProxyFactoryBean messageProxyFactoryBean = new MessageProxyFactoryBean(new DetailMessage("hello"));
        messageProxyFactoryBean.addAdvisor(new MessagePointcut("getT*"), new MessageAdvice());

        //when
        Message message = messageProxyFactoryBean.getMessage();

        //then
        assertThat(message.getText()).isEqualTo("HELLO");
        assertThat(message.getPlainText()).isEqualTo("hello");
    }
}
