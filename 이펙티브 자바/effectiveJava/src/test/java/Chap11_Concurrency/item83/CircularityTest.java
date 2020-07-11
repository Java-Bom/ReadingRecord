package Chap11_Concurrency.item83;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/07/11
 */
class CircularityTest {

    static class A{
        static int number2 = B.number;
        static int number = 1;
    }

    static class B{
        static int number = A.number;
    }

    public static void main(String[] args) {
        System.out.println(A.number2);
    }

}