package Chap12_Serialization.item90;

import java.io.Serializable;

public class Foo implements Serializable {
    public static final Foo INSTANCE = new Foo("FIX");
    private String value;

    public Foo(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private Object writeReplace() {
        return new FooProxy(this);
    }

    static class FooProxy implements Serializable {
        private String value;

        public FooProxy(Foo foo) {
            this.value = foo.value;
        }


        private Object readResolve() {
            return new Foo(this.value);
        }
    }

//    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
//        objectInputStream.defaultReadObject();
//
//        this.value = "READ_OBJECT";
//    }

//    private Object readResolve() {
//        return Foo.INSTANCE;
//    }
}
