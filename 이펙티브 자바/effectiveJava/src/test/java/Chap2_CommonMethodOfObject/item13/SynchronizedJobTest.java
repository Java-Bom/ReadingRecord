package Chap2_CommonMethodOfObject.item13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

class SynchronizedJobTest {

    @Test
    @DisplayName("clone 메소드를 Thread safe 하게 구현해야 한다.")
    void testClone() throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(2);
        CountDownLatch latch2 =  new CountDownLatch(1);
        SynchronizedJob synchronizedJob = new SynchronizedJob();

        Thread thread1 = new Thread(job(synchronizedJob,latch,latch2));
        Thread thread2 = new Thread(cloneJob(synchronizedJob,latch,latch2));
        thread1.setName("쓰레드 1");
        thread2.setName("쓰레드 2");
        thread1.start();
        thread2.start();

        latch.await();
    }

    Runnable job(SynchronizedJob sync, CountDownLatch latch, CountDownLatch latch2) {
        return () -> {
            sync.reduceCountWithLock(latch2);
            latch.countDown();
        };
    }

    Runnable cloneJob(SynchronizedJob sync, CountDownLatch latch,CountDownLatch latch2) {
        return () -> {
            try {
                latch2.await();
                SynchronizedJob clone = sync.clone();
                System.out.println(Thread.currentThread().getName() + "clone 객체 count 값 : " + clone.cloneCount);
                if(clone.cloneCount == 0) {
                    latch.countDown();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
    }
}
