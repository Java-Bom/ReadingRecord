package Chap3_ClassAndInterface.vending;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VendingTest {

    @DisplayName("시뮬레이트한 다중 상속 구현")
    @Test
    void name() {
        SnackVending snackVending = new SnackVending();
        snackVending.process();
    }

    @DisplayName("추상 골격 클래스를 이용한 결과")
    @Test
    void name1() {
        BaverageVending baverageVending = new BaverageVending();
        CoffeeVending coffeeVending = new CoffeeVending();
        baverageVending.process();
        System.out.println("==============================");
        coffeeVending.process();
    }
}
