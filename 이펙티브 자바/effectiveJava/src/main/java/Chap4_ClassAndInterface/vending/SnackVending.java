package Chap4_ClassAndInterface.vending;

public final class SnackVending extends VendingManufacturer implements Vending {
    InnerAbstractVending innerAbstractVending = new InnerAbstractVending();

    public void start() {
        innerAbstractVending.start();
    }

    @Override
    public void chooseProduct() {
        innerAbstractVending.chooseProduct();
    }

    public void stop() {
        innerAbstractVending.stop();
    }

    @Override
    public void process() {
        printManufacturerName();
        innerAbstractVending.process();
    }

    private class InnerAbstractVending extends AbstractVending {

        @Override
        public void chooseProduct() {
            System.out.println("choose product");
            System.out.println("chocolate");
            System.out.println("cracker");
        }
    }

}
