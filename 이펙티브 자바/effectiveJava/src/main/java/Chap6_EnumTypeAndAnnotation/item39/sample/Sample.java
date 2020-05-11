package Chap6_EnumTypeAndAnnotation.item39.sample;

public class Sample {

    @CustomTest
    public static void m1() {
        System.out.println("성공.");
    }

    public static void m2() {
        System.out.println("무시.");
    }

    @CustomTest
    public static void m3() {
        throw new RuntimeException("실패.");
    }

    public static void m4() {
        System.out.println("무시.");
    }

    @CustomTest
    public void m5() {
        System.out.println("정적 메서드가 아니다. 잘못 사용한 예");
    }

    public static void m6() {
        System.out.println("무시.");
    }

    @CustomTest
    public static void m7() {
        throw new RuntimeException("실패.");
    }

    public static void m8() {
        System.out.println("무시.");
    }

}
