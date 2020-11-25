package com.javabom.toby.chapter1.term.제어의_역전;

public class IoCContainer {

    /**
     * IoC: 제어의 역전
     * UserService의 UserRepository 구현체에 대한 생성의 책임을 IoCContainer에 위임한다.
     *
     * @return
     */
    public UserService javabomUserService() {
        return new UserService(new JavabomUserRepository());
    }
}
