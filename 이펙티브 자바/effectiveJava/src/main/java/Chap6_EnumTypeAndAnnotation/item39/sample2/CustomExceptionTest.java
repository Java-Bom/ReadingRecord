package Chap6_EnumTypeAndAnnotation.item39.sample2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomExceptionTest {
    Class<? extends Throwable> value();
}
