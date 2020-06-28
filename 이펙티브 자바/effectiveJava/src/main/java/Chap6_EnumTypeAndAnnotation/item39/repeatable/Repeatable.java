package Chap6_EnumTypeAndAnnotation.item39.repeatable;

public class Repeatable {

    @ExceptionTest(IllegalArgumentException.class)
    @ExceptionTest(NullPointerException.class)
    public void repeatAnnotationMethod() {

    }

    @ExceptionTest(IllegalArgumentException.class)
    public void notRepeatAnnotationMethod() {

    }
}
