package Chap6_EnumTypeAndAnnotation.item39.sample2;

public class Sample2 {

    //익셉션 발생으로 성공해야한다.
    @CustomExceptionTest(ArithmeticException.class)
    public static void m1() {
        int i = 0;
        i = i / i;
    }

    //다른 예외가 발생하여 실패해야한다.
    @CustomExceptionTest(ArithmeticException.class)
    public static void m2() {
        int[] a = new int[0];
        int i = a[1];
    }

    //예외가 없어서 실패해야 한다.
    @CustomExceptionTest(ArithmeticException.class)
    public static void m3() {
    }
}
