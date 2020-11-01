package com.javabom.toby.chapter1.pattern.팩토리_메소드_패턴;

/**
 * Created by jyami on 2020/11/01
 */
public class SeafoodPizza extends Pizza {

    @Override
    public Pizza getPizza() {
        return new SeafoodPizza();
    }

}
