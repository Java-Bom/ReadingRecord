package com.javabom.toby.chapter1.pattern.팩토리_메소드;

/**
 * Created by jyami on 2020/11/01
 */
public class Card {

    private final CardType cardType;

    private Card(CardType cardType) {
        this.cardType = cardType;

    }

    public enum CardType {
        LOTTE, HYUNDAI
    }


    public static Card ofLotte() {
        return new Card(CardType.LOTTE);
    }

    public static Card ofHyundai() {
        return new Card(CardType.HYUNDAI);
    }
}
