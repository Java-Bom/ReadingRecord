package Chap9_GeneralProgrammingPrinciple.item59;

import java.util.Random;

public class RandomTest {
    static Random rnd = new Random();

    static int random(int n) {
        return Math.abs(rnd.nextInt()) % n;
    }

    public static void main(String[] args) {
        System.out.println(Math.abs(Integer.MIN_VALUE) % 7);
    }
}
