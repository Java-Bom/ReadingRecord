package com.javabom.toby.chapter6.term.aop용어.프록시;

import com.javabom.toby.chapter6.term.aop용어.프록시.DetailMessage;
import com.javabom.toby.chapter6.term.aop용어.프록시.Message;
import com.javabom.toby.chapter6.term.aop용어.프록시.MessageHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

class DetailMessageHandlerTest {

    @Test
    @DisplayName("프록시 객체 동작")
    void name() {
        //given

        //when
        Message message = (Message) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{Message.class},
                new MessageHandler(new DetailMessage("test")));
        //then
        assertThat(message.getText()).isEqualTo("TEST");
    }
}
