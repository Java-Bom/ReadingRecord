package com.javabom.toby.chapter1.term.제어의_역전;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IoCContainerTest {

    @DisplayName("클라이언트는 IoCContainer를 통해 UserService를 얻는다")
    @Test
    void ioc() {
        IoCContainer container = new IoCContainer();
        UserService userService = container.javabomUserService();
    }


}