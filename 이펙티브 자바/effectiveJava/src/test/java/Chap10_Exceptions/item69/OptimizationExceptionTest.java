package Chap10_Exceptions.item69;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

class OptimizationExceptionTest {

    @Test
    @DisplayName("mod 값이 바뀐 hasMap은 다시 사용할 수 없다.")
    void name() throws InterruptedException {
        Map<Integer, Integer> map = new HashMap<>();

        map.put(1, 1);
        map.put(2, 2);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);

        new Thread(() -> {
            try {
                countDownLatch.await();
                map.put(3, 3);
                countDownLatch2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (Integer integer : map.keySet()) {
            countDownLatch.countDown();
            System.out.println(integer);
            countDownLatch2.await();
        }
    }


}