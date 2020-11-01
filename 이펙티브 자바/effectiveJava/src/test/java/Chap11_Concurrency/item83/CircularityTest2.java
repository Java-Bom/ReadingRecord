package Chap11_Concurrency.item83;

/**
 * Created by jyami on 2020/08/02
 */
public class CircularityTest2 {


    static class A {
        static Integer number2;
        static Integer number = C.number;

        static void getNumber() {
            if (number2 == null) {
                number2 = B.number;
            }
        }
    }

    static class B {
        static Integer number = A.number;
    }

    static class C {
        static Integer number = 1;
    }

    public static void main(String[] args) {
        A.getNumber();
        System.out.println(A.number2);
        System.out.println(B.number);
    }

}
