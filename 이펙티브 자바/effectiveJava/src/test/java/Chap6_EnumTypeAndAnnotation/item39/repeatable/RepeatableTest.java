package Chap6_EnumTypeAndAnnotation.item39.repeatable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Java6Assertions.assertThat;

class RepeatableTest {

    @DisplayName("두개이상, 반복가능 애너테이션이 달려있다면 Container를 인식한다.")
    @Test
    void name() throws NoSuchMethodException {
        //given
        Method repeatAnnotationMethod = Repeatable.class.getMethod("repeatAnnotationMethod");

        //when
        boolean annotationPresent = repeatAnnotationMethod.isAnnotationPresent(java.lang.annotation.Repeatable.class);
        boolean exceptionTestAnnotationPresent = repeatAnnotationMethod.isAnnotationPresent(ExceptionTest.class);
        boolean exceptionTestContainerAnnotationPresent = repeatAnnotationMethod.isAnnotationPresent(ExceptionTestContainer.class);

        //then
        assertThat(repeatAnnotationMethod).isNotNull();
        assertThat(annotationPresent).isFalse();
        assertThat(exceptionTestAnnotationPresent).isFalse();
        assertThat(exceptionTestContainerAnnotationPresent).isTrue();
    }

    @DisplayName("단 한개의 반복가능 애너테이션이 달려있다면 Container를 인식하지 않는다..")
    @Test
    void name2() throws NoSuchMethodException {
        //given
        Method notRepeatAnnotationMethod = Repeatable.class.getMethod("notRepeatAnnotationMethod");

        //when
        boolean annotationPresent = notRepeatAnnotationMethod.isAnnotationPresent(java.lang.annotation.Repeatable.class);
        boolean exceptionTestAnnotationPresent = notRepeatAnnotationMethod.isAnnotationPresent(ExceptionTest.class);
        boolean exceptionTestContainerAnnotationPresent = notRepeatAnnotationMethod.isAnnotationPresent(ExceptionTestContainer.class);

        //then
        assertThat(notRepeatAnnotationMethod).isNotNull();
        assertThat(annotationPresent).isFalse();
        assertThat(exceptionTestAnnotationPresent).isTrue();
        assertThat(exceptionTestContainerAnnotationPresent).isFalse();
    }
}