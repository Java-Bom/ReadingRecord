package item20.vending;

public abstract class OnlyVending {
    public void start() {
        System.out.println("vending start");
    }

    public void stop() {
        System.out.println("stop vending");
    }

    abstract void chooseProduct();

    public void process() {
        start();
        chooseProduct();
        stop();
    }
}
