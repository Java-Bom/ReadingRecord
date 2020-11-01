package com.javabom.toby.chapter1.pattern.팩토리_메소드_패턴;

/**
 * Created by jyami on 2020/11/01
 */
public abstract class Pizza {

    protected abstract Pizza getPizza();

    public void eat() {
        Pizza pizza = this.getPizza();
        System.out.println(pizza);
    }

}
