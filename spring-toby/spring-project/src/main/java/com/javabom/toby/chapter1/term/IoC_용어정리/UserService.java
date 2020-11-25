package com.javabom.toby.chapter1.term.IoC_용어정리;

/*
 * UserService 는 UserRepository 인터페이스에 의존함으로써 느슨한 결합을 가진다.
 * 의존관계 주입의 특징 1) 이 코드에서 DefaultUserRepository와의 의존관계는 드러나지 않는다
 */
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {

    }
}
