package com.javabom.toby.chapter6.term.확장.확장포인트컷;

import com.javabom.toby.chapter6.term.확장.ExtendedAopConfiguration;
import com.javabom.toby.chapter6.term.확장.model.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = ExtendedAopConfiguration.class)
class NameMatchMessagePointcutTest {
    @Autowired
    private Message targetMessage;

    @Autowired
    private Message nonTargetMessage;

    @DisplayName("다이나믹 프록시 테스트")
    @Test
    void extendedPointcutTest() {
        assertThat(targetMessage).isInstanceOf(Proxy.class);
        assertThat(nonTargetMessage).isNotInstanceOf(Proxy.class);
        assertThat(targetMessage.getMessage()).isEqualTo("HELLO");
        assertThat(nonTargetMessage.getMessage()).isEqualTo("hello");
    }
}
