package Chap1_GenerateObjectAndDestroy.item1;

import Chap1_GenerateObjectAndDestroy.item1.constructor.Foo;
import Chap1_GenerateObjectAndDestroy.item1.constructor.FooRepository;
import Chap1_GenerateObjectAndDestroy.item1.constructor.SingletonFoo;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class StaticConstructorTest {

    @DisplayName("정적 팩터리 메서드는 싱글턴 클래스에서 쓰일 수 있다")
    @Test
    public void singletonFoo(){
        SingletonFoo singletonFoo1 = SingletonFoo.getInstance();
        SingletonFoo singletonFoo2 = SingletonFoo.getInstance();

        Foo foo1 = new Foo(new FooRepository()); // DI 패턴
        Foo foo2 = new Foo(new FooRepository());

        assertSame(singletonFoo1, singletonFoo2);
        assertNotSame(foo1, foo2);
    }




}
