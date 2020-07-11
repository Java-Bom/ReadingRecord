package Chap11_Concurrency.item83;

/**
 * Created by jyami on 2020/07/11
 */

import java.util.*;

public class Worker extends Thread {

    private volatile boolean quittingTime = false;

    public void run() {
        while (!quittingTime)
            pretendToWork();
        System.out.println("Beer is good");
    }

    private void pretendToWork() {
        try {
            Thread.sleep(300); // Sleeping on the job?
        } catch (InterruptedException ex) {
        }
    }

    // It's quitting time, wait for worker - Called by good boss
    synchronized void quit() throws InterruptedException {
        quittingTime = true;
        join();
    }

    // Rescind quitting time - Called by evil boss
    synchronized void keepWorking() {
        quittingTime = false;
    }


    public static void main(String[] args) throws InterruptedException {

        final Worker worker = new Worker();
        worker.start();

        Timer t = new Timer(true); // Daemon thread
        t.schedule(new TimerTask() {
            public void run() {
                worker.keepWorking();
            }
        }, 500);

        Thread.sleep(400);
        worker.quit();
    }

}

