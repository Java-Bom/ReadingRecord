package com.javabom.toby.chapter6.term.aop용어.팩토리빈;

import com.javabom.toby.chapter6.term.aop용어.AopConfiguration;
import com.javabom.toby.chapter6.term.aop용어.팩토리빈.MessageFactoryBean;
import com.javabom.toby.chapter6.term.aop용어.프록시.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AopConfiguration.class)
class MessageFactoryBeanTest {
    @Autowired
    private MessageFactoryBean factoryBean;

    @Test
    @DisplayName("펙토리 빈 테스트")
    void name() throws Exception {
        //given

        //when
        Message message = (Message) factoryBean.getObject();
        //then
        assertThat(message.getText()).isEqualTo("TEST");
    }
}
