package Chap5_Generic.item30;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Chap5_Generic.item30.LottePayCard.Sale.LOTTE_DEPT;

class LottePayCardTest {

    @DisplayName("시뮬레이트한 셀프타입 관용구")
    @Test
    void simulate() {
        LottePayCard lottePayCard = new LottePayCard.Builder(LOTTE_DEPT)
                .addBenefit(PayCard.Benefit.POINT)
                .build();
    }

}
