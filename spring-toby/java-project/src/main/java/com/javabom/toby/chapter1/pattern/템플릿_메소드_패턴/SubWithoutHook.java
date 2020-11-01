package com.javabom.toby.chapter1.pattern.템플릿_메소드_패턴;

/**
 * Created by jyami on 2020/11/01
 */

public class SubWithoutHook extends Super {
    @Override
    public void abstractMethod() {
        System.out.println("슈퍼클래스의 기능을 오버라이드하거나 구현해서 특정 기능으로 구현한다.");
        System.out.println("SubWithoutHook 클래스에서 구현한 메서드");
    }
}
