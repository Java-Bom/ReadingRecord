package Chap9_GeneralProgrammingPrinciple.item62;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

class RealMyThreadLocalTest {
    CountDownLatch latch = new CountDownLatch(2);
    Runnable run = () -> {
        String name = Thread.currentThread().getName();
        RealMyThreadLocal<String> threadLocal = new RealMyThreadLocal<>();
        threadLocal.set(name);

        CallThreadMap<String> callThreadMap = new CallThreadMap<>();
        System.out.println(name + " Thread - " + callThreadMap.getMyValue());

        latch.countDown();
    };

    @Test
    void name() throws InterruptedException {
        Thread thread1 = new Thread(run);
        thread1.setName("Minsu");
        Thread thread2 = new Thread(run);
        thread2.setName("Cheolsu");

        thread1.start();
        thread2.start();

        latch.await();
    }

    static class CallThreadMap<T> {

        public T getMyValue() {
            RealMyThreadLocal<T> threadLocal = new RealMyThreadLocal<>();
            return threadLocal.get();
        }
    }
}