package Chap11_Concurrency.item82;

/**
 * Created by jyami on 2020/07/11
 */
public class LoggingWidget extends Widget {
    public void doSomething() {
        System.out.println(toString() + ": calling doSomething : logging widget");
        super.doSomething();
    }

    public void printlnTest(){
        synchronized (this){
            System.out.println("print");
        }
    }
}

