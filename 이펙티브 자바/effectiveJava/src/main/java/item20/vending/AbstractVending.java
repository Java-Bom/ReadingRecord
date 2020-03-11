package item20.vending;

public abstract class AbstractVending implements Vending {
    public void start() {
        System.out.println("vending start");
    }

    public void stop() {
        System.out.println("stop vending");
    }

    @Override
    public final void process() { // public 애들은 내부에서 호출하지 않는게 최고, process 메소드 자체를 변경하면 좋을덧
        start(); // 요게 문제다.
        chooseProduct();
        stop(); // 요게 문제야.
    }
}
