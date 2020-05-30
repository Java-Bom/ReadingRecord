package Chap7_LambdaAndStream.item50;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyami on 2020/05/30
 */
class PeriodTest {

    static Period period = null;

    @Test
    @DisplayName("유효성 검사를 먼저 할 때")
    void checkingValid() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);

        Date start = new Date();
        start.setTime(1000);
        Date end = new Date();
        start.setTime(10000);


        new Thread(() -> {
            period = new Period(start, end, latch, latch2);
            System.out.println("maker : " + period);
            latch3.countDown();
        }).start();

        latch2.await(); // 검증이 끝날 때까지 걸려있어야 한다.
        end.setTime(100);
        latch.countDown();
        latch3.await(); // 생성이 끝날 때까지 걸려있어야 한다.

        // start가 end보다 나중이다.
        assertThat(period.getStart().after(period.getEnd())).isTrue();
    }
}