package Chap5_Generic.item28;


public class VarArgsCalculator {
    public static int sum(int... source) {
        int result = 0;
        for (int i : source) {
            result += i;
        }
        return result;
    }

    @SafeVarargs
    public static <E> int sumGeneric(E... source) {
        return 1;
    }
}
