package Chap11_Concurrency.item82;

/**
 * Created by jyami on 2020/07/11
 */
public class Widget {

    final private Object lock = new Object();

    public void doSomething() {
        synchronized (lock){
            System.out.println(toString() + ": calling doSomething : widget");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        LoggingWidget loggingWidget = new LoggingWidget();
        new Thread(() -> {
            loggingWidget.doSomething();
        }).start();
        new Thread(() -> {
            loggingWidget.printlnTest();
        }).start();
    }
}

