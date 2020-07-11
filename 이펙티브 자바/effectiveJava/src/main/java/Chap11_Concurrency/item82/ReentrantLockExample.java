package Chap11_Concurrency.item82;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jyami on 2020/07/11
 */
public class ReentrantLockExample {

    static ReentrantLock lock = new ReentrantLock();

    public static void outer() {
        lock.lock();
        boolean locked = lock.isLocked();
        System.out.println(locked);
        inner();
        lock.unlock();
    }

    public static void inner() {
        System.out.println(lock.isLocked());
        lock.lock();
        System.out.println("hello");
        System.out.println(lock.isLocked());
        lock.unlock();
    }

    public static void main(String[] args) {
        outer();
    }
}
