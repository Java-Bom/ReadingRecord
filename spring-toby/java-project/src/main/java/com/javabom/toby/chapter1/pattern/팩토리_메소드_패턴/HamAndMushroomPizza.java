package com.javabom.toby.chapter1.pattern.팩토리_메소드_패턴;

/**
 * Created by jyami on 2020/11/01
 */
public class HamAndMushroomPizza extends Pizza {

    @Override
    protected Pizza getPizza() {
        return new SeafoodPizza();
    }
}
