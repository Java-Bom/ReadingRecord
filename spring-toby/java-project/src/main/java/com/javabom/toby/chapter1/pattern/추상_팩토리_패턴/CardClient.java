package com.javabom.toby.chapter1.pattern.추상_팩토리_패턴;

/**
 * 클라이언트 코드 내부 어디에도 구체클래스는 드러나지 않는다
 */
public class CardClient {
    private final CardFactory cardFactory;

    public CardClient(final CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    public void useCard() {
        Card card = cardFactory.createCard();
        card.pay();
    }

    public void userCardApp() {
        CardApp cardApp = cardFactory.createCardApp();
        cardApp.use();
    }
}
