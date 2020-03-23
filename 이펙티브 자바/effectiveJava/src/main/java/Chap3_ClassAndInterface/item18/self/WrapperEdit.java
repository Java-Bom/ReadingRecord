package Chap3_ClassAndInterface.item18.self;

/**
 * Created by jyami on 2020/02/22
 */
public class WrapperEdit {
    private final WrappedObject wrappedObject;

    public WrapperEdit(WrappedObject wrappedObject) {
        this.wrappedObject = wrappedObject;
    }

    public void doSmething() {
        wrappedObject.doSomething();
    }

}
