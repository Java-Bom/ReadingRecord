package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;

public class LotteCardFactory implements CardFactory {
    @Override
    public Card createCard() {
        /**
         * 롯데카드 만의 생성로직
         */
        return new LotteCard();
    }

    @Override
    public CardApp createCardApp() {
        /**
         * 롯데카드 앱만의 생성로직
         */
        return new LotteCardApp();
    }
}
