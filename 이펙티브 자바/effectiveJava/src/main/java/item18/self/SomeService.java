package item18;

/**
 * Created by jyami on 2020/02/22
 */
final class SomeService {

    void performAsync(SomethingWithCallback callback) {
        new Thread(() -> {
            perform();
            callback.call();
            Wrapper.wrapper.call();
        }).start();
    }

    void perform() {
        System.out.println("Service is being performed.");
    }

    public static void main(String[] args) {
        SomeService   service       = new SomeService();
        WrappedObject wrappedObject = new WrappedObject(service);
        Wrapper       wrapper       = new Wrapper(wrappedObject);

        wrapper.doSomething();
    }
}
