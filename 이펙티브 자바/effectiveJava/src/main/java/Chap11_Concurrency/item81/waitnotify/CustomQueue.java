package Chap11_Concurrency.item81.waitnotify;

import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue {

    private final Queue<String> queue = new LinkedList<>();

    public void safe() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            this.remove();
        }
    }

    public void notSafe() throws InterruptedException {
        synchronized (queue) {
            if (queue.isEmpty()) {
                queue.wait();
            }
            this.remove();
        }
    }

    public void addWithNotify() {
        queue.add("ADD WITH NOTIFY");
        queue.notify();
    }

    public void remove() {
        System.out.println(this.queue.remove());
    }

    public void add() {
        queue.add("JUST ADD");
    }

}
