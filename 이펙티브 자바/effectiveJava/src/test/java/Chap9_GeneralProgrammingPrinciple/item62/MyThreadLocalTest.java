package Chap9_GeneralProgrammingPrinciple.item62;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

class MyThreadLocalTest {
    CountDownLatch latch = new CountDownLatch(2);
    Runnable run1 = () -> {
        //이름 Minsu
        String name = Thread.currentThread().getName();
        MyThreadLocal.set("1", name + " hi!");
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {

        }

        System.out.println("[" + name + "] - " + MyThreadLocal.get("1"));
        latch.countDown();
    };
    Runnable run2 = () -> {
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {

        }
        //이름 Cheolsu
        String name = Thread.currentThread().getName();
        MyThreadLocal.set("1", name + " hi!");

        System.out.println("[" + name + "] - " + MyThreadLocal.get("1"));
        latch.countDown();
    };

    @Test
    void name() throws InterruptedException {
        Thread thread1 = new Thread(run1);
        thread1.setName("Minsu");
        Thread thread2 = new Thread(run2);
        thread2.setName("Cheolsu");

        thread1.start();
        thread2.start();

        latch.await();

    }
}