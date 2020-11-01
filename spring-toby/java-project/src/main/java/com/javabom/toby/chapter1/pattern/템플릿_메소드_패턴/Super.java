package com.javabom.toby.chapter1.pattern.템플릿_메소드_패턴;

/**
 * Created by jyami on 2020/11/01
 */
public abstract class Super {

    protected void hookMethod(){
        System.out.println("선택적으로 오버라이드 가능한 훅 메서드");
    }

    public abstract void abstractMethod();
}
