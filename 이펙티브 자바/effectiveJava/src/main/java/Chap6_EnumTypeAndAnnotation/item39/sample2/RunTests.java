package Chap6_EnumTypeAndAnnotation.item39.sample2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class RunTests {
    public static void main(String[] args) throws Exception {
        // CustomExceptionTest 어노테이션이 있는 메소드의 갯수
        int tests = 0;
        // 통과한 테스트의 갯수
        int passed = 0;

        Class<?> testClass = Class.forName("Chap6_EnumTypeAndAnnotation.item39.sample2.Sample2");

        for (Method method : testClass.getDeclaredMethods()) {

            System.out.println("\n" + method);
            if (method.isAnnotationPresent(CustomExceptionTest.class)) {
                tests++;
                // 정적인 메서드만 추려낸다.
                if (Modifier.isStatic(method.getModifiers())) {
                    try {
                        method.invoke(null);
                        System.out.println("실패하지 못했다. 테스트 실패");
                    } catch (InvocationTargetException e) {
                        Throwable throwable = e.getCause();
                        Class<? extends Throwable> exceptionType = method.getAnnotation(CustomExceptionTest.class).value();
                        if (exceptionType.isInstance(throwable)) {
                            passed++;
                        } else {
                            System.out.printf("테스트 실패, 기대 : %s, 실제 : %s\n", exceptionType.getName(), throwable);
                        }
                    }
                    continue;
                }

                System.out.println("잘못 사용(비정적)한 @CustomTest : " + method);
                continue;
            }

            System.out.println("@CustomTest 가 없어서 무시 되었다.");
        }

        System.out.printf("\n성공 : %d, 실패 : %d\n", passed, tests - passed);
    }
}
