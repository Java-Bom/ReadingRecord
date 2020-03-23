package Chap1_GenerateObjectAndDestroy.item5;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LambdaFactory {
    private static final int TYPE_A = 1;
    private static final int TYPE_B = 2;
    private static final int TYPE_C = 3;
    private static final int TYPE_D = 4;

    final static Map<Integer, Supplier<? extends ITypeFactory>> map = new HashMap<>();
    static {
        map.put(0, ITypeFactory::new);
        map.put(TYPE_A, A::new);
        map.put(TYPE_B, B::new);
        map.put(TYPE_C, C::new);
        map.put(TYPE_D, D::new);

    }
}

class ITypeFactory {}

class A extends ITypeFactory {}

class B extends ITypeFactory {}

class C extends ITypeFactory {}

class D extends ITypeFactory {}

