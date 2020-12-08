package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;


public class SamsungCardFactory implements CardFactory {
    @Override
    public Card createCard() {
        /**
         * 삼성카드만의 생성로직
         */
        return new SamsungCard();
    }

    @Override
    public CardApp createCardApp() {
        /**
         * 삼성카드 앱만의 생성로직
         */
        return new SamsungCardApp();
    }
}
