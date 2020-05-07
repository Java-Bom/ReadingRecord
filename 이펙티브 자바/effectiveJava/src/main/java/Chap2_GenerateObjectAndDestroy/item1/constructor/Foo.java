package Chap2_GenerateObjectAndDestroy.item1.constructor;

public class Foo {

    private final FooRepository fooRepository;

    // DI 패턴 사용 시 생성자 구현이 필요하다.
    public Foo(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

}
