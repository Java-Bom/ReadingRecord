package Chap2_CommonMethodOfObject.item13;

public class Foo implements Cloneable {
    int num;

    public Foo() {
        System.out.println("---------------------");
        System.out.println("Foo constructor");
        System.out.println("---------------------");
    }

    public Foo(int num) {
        System.out.println("---------------------");
        System.out.println("Foo constructor");
        System.out.println("---------------------");
        this.num = num;
    }

    @Override
    public Foo clone() {
        try {
            System.out.println("---------------------");
            System.out.println("Foo Clone");
            System.out.println("---------------------");
            return (Foo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }
}
