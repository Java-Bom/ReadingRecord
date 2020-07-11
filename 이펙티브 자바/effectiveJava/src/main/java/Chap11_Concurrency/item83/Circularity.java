package Chap11_Concurrency.item83;

/**
 * Created by jyami on 2020/07/11
 */
public class Circularity {
    static class A {
        static int number = 1;
        static int number2 = B.number;
    }

    static class B {

        static Integer number;

        private synchronized static int getField() {
            if (number == null)
                number = A.number;
            return number;

        }
    }

    public static void main(String[] args) {
        int field = B.getField();
    }
}

