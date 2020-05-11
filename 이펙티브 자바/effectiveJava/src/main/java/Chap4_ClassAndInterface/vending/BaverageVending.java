package Chap4_ClassAndInterface.vending;

public class BaverageVending extends AbstractVending implements Vending {

    @Override
    public void chooseProduct() {
        System.out.println("choose menu");
        System.out.println("coke");
        System.out.println("energy drink");
    }
}
