package item1.constructor;

public class FooMain {
    public static void main(String[] args) {
        // Constructor -> FooRepository DI
        Foo foo = new Foo(new FooRepository());

        // Static Factory Method -> Singleton Foo
        SingletonFoo singletonFoo = SingletonFoo.getInstance();
    }
}
