package Chap9_GeneralProgrammingPrinciple.item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class ReflectionMain {
    public static void main(String[] args) {
        setTester("Chap9_GeneralProgrammingPrinciple.item65.PrivateConsSet");
        setTester("Chap9_GeneralProgrammingPrinciple.item65.ConsThrowSet");
        setTester("java.util.ArrayList");
    }

    private static void setTester(String className) {
        Class<? extends Set<String>> cl = null;
        try {
            cl = (Class<? extends Set<String>>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            fatalError("클래스를 찾을 수 없음");
        }

        Constructor<? extends Set<String>> cons = null;
        try {
            cons = cl.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            fatalError("매개변수 없는 생성자를 찾을 수 없음");
        }

        Set<String> s = null;

        try {
            s = cons.newInstance();
        } catch (IllegalAccessException e) {
            fatalError("생성자 접근 불가");
        } catch (InstantiationException e) {
            fatalError("클래스 인스턴스화 불가");
        } catch (InvocationTargetException e) {
            fatalError("생성자 예외 throw: " + e.getCause());
        } catch (ClassCastException e) {
            fatalError("Set을 구현하지 않은 클래스");
        } finally {
            return;
        }
    }

    private static void fatalError(String msg) {
        System.out.println(msg);
    }
}
