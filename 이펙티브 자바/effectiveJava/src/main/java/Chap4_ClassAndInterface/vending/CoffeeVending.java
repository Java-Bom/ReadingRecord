package Chap4_ClassAndInterface.vending;

public class CoffeeVending extends AbstractVending implements Vending {

//    @Override
//    public void start() {
//        System.out.println("vending start");
//    }

    @Override
    public void chooseProduct() {
        System.out.println("choose menu");
        System.out.println("americano");
        System.out.println("cafe latte");
    }

//    @Override
//    public void stop() {
//        System.out.println("stop vending");
//    }
//
//    @Override
//    public void process() {
//        start();
//        chooseProduct();
//        stop();
//    }
}
