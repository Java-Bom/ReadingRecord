package Chap4_Generic.item30;

import java.util.function.UnaryOperator;

public class ToString {

    private static UnaryOperator<Object> IDENTITY_FN = Object::toString;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }
}
