package Chap11_Concurrency.item81.waitnotify;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

class CustomQueueTest {

    //  consumer1 in sync block
    //  consumer1 waiting
    //  consumer2 in sync block
    //  consumer2 waiting
    //  provider in sync block
    //  provider add item
    //  provider notifyAll
    //  consumer2 wake up
    //  consumer2 remove item
    //  consumer1 wake up
    //  consumer1 remove item
    // java.util.NoSuchElementException
    @Test
    void notSafe() throws InterruptedException {
        Queue<String> queue = new LinkedList<>();

        CountDownLatch consumer1CountDownLatch = new CountDownLatch(1);
        CountDownLatch consumer2CountDownLatch = new CountDownLatch(1);
        CountDownLatch endCountDown = new CountDownLatch(1);

        Thread consumer1 = new Thread(
                () -> {
                    try {
                        synchronized (queue) {
                            System.out.println("consumer1 in sync block ");

                            if (queue.isEmpty()) {
                                System.out.println("consumer1 waiting ");
                                consumer1CountDownLatch.countDown();
                                queue.wait();
                                System.out.println("consumer1 wake up");
                            }
                            System.out.println("consumer1 remove item");

                            assertThatThrownBy(queue::remove)
                                    .isInstanceOf(NoSuchElementException.class);
                            endCountDown.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread consumer2 = new Thread(
                () -> {
                    try {
                        synchronized (queue) {
                            System.out.println("consumer2 in sync block ");
                            if (queue.isEmpty()) {
                                System.out.println("consumer2 waiting ");
                                consumer2CountDownLatch.countDown();
                                queue.wait();
                                System.out.println("consumer2 wake up");
                            }
                            System.out.println("consumer2 remove item");
                            queue.remove();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        Thread provider = new Thread(
                () -> {
                    try {
                        synchronized (queue) {
                            System.out.println("provider in sync block ");
                            System.out.println("provider add item ");
                            queue.add("SOME THING");
                            System.out.println("provider notifyAll");
                            queue.notifyAll();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

        consumer1.start();
        consumer1CountDownLatch.await();

        consumer2.start();
        consumer2CountDownLatch.await();

        provider.start();
        endCountDown.await();

        assertThat(endCountDown.getCount()).isEqualTo(0);
    }

    @Test
    void safe() throws InterruptedException {
        Queue<String> queue = new LinkedList<>();

        CountDownLatch consumer1CountDownLatch = new CountDownLatch(1);
        CountDownLatch consumer2CountDownLatch = new CountDownLatch(1);
        CountDownLatch endCountDown = new CountDownLatch(1);

        Thread consumer1 = new Thread(
                () -> {
                    try {
                        synchronized (queue) {
                            System.out.println("consumer1 in sync block ");

                            while (queue.isEmpty()) {
                                System.out.println("consumer1 waiting ");
                                consumer1CountDownLatch.countDown();
                                queue.wait();
                                System.out.println("consumer1 wake up");
                            }
                            System.out.println("consumer1 remove item");

                            queue.remove();
                            endCountDown.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread consumer2 = new Thread(
                () -> {
                    try {
                        synchronized (queue) {
                            System.out.println("consumer2 in sync block ");
                            while (queue.isEmpty()) {
                                System.out.println("consumer2 waiting ");
                                consumer2CountDownLatch.countDown();
                                queue.wait();
                                System.out.println("consumer2 wake up");
                            }
                            System.out.println("consumer2 remove item");
                            queue.remove();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        Thread provider = new Thread(
                () -> {
                    try {
                        synchronized (queue) {
                            System.out.println("provider in sync block ");
                            System.out.println("provider add item ");
                            queue.add("SOME THING");
                            System.out.println("provider notifyAll");
                            queue.notifyAll();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

        consumer1.start();
        consumer1CountDownLatch.await();

        consumer2.start();
        consumer2CountDownLatch.await();

        provider.start();
        endCountDown.await(3, TimeUnit.MILLISECONDS);

        assertThat(endCountDown.getCount()).isEqualTo(1);
    }
}