package com.javabom.toby.chapter1.pattern.템플릿_메소드_패턴;

/**
 * Created by jyami on 2020/11/01
 */
public class SubWithHook extends Super{

    @Override
    protected void hookMethod() {
        super.hookMethod();
        System.out.println("선택적으로 오버라이드 가능한 훅 메서드 : 오버라이드해서 사용");
    }

    @Override
    public void abstractMethod() {
        System.out.println("SubWithHook 클래스에서 구현한 메서드");
    }
}
