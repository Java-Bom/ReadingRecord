package Chap9_GeneralProgrammingPrinciple.item62;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

class ThreadLocalUseKeyTest {
    CountDownLatch latch = new CountDownLatch(2);
    Runnable run1 = () -> {
        ThreadLocalUseKey.Key myKey = ThreadLocalUseKey.getKey();
        String name = Thread.currentThread().getName();
        System.out.println(name + " - " + myKey);
        myKey.set(myKey, name);

        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {

        }
        System.out.println((String) myKey.get(myKey));
        latch.countDown();
    };

    @Test
    void name() throws InterruptedException {
        Thread thread1 = new Thread(run1);
        thread1.setName("Minsu");
        Thread thread2 = new Thread(run1);
        thread2.setName("Cheolsu");

        thread1.start();
        thread2.start();

        latch.await();
    }
}