package item17.self;

/**
 * Created by jyami on 2020/02/22
 */

class WrappedObject implements SomethingWithCallback {

    private final SomeService service;

    WrappedObject(SomeService service) {
        this.service = service;
    }

    @Override
    public void doSomething() {
        service.performAsync(this);
    }

    @Override
    public void call() {
        System.out.println("WrappedObject callback!");
    }
}
