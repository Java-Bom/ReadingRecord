package Chap9_GeneralProgrammingPrinciple.item57;

public class ExpensiveLoop {

    public static void main(String[] args) {
        for (int i = 0, k = expensiveComputation(); i < k; i++) {

        }

        System.out.println("===============");

        for (int i = 0; i < expensiveComputation(); i++) {

        }
    }

    public static int expensiveComputation() {
        System.out.println("호출됩니다!");
        return 9;
    }
}
