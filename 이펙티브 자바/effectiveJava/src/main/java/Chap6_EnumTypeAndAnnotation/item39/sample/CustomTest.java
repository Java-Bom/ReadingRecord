package Chap6_EnumTypeAndAnnotation.item39.sample;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 테스트 메서드임을 선언하는 애너테이션.
 * 매개변수가 없는 정적 메서드 전용
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomTest {
}
