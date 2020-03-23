package Chap3_ClassAndInterface.item18.self;

/**
 * Created by jyami on 2020/02/22
 */

class Wrapper implements SomethingWithCallback {

    public static Wrapper wrapper; // 모든게 접근할 수 있음. // 내부 객체들만 접근하고 싶게는 못함.
    private final WrappedObject wrappedObject;
    public Wrapper wrapperThis;

    Wrapper(WrappedObject wrappedObject) {
        this.wrappedObject = wrappedObject;
        this.wrapperThis = this;
    }

    @Override
    public void doSomething() {
        wrapper = this;
        wrappedObject.doSomething();
    }

    void doSomethingElse() {
        System.out.println("We can do everything the wrapped object can, and more!");
    }

    @Override
    public void call() {
        System.out.println("Wrapper callback!");
    }
}
