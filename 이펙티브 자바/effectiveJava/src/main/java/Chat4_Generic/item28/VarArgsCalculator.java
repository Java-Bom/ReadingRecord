package Chat4_Generic.item28;


public class VarArgsCalculator {
    public static int sum(int... source) {
        int result = 0;
        for (int i : source) {
            result += i;
        }
        return result;
    }

    @SafeVarargs
    public static <E> int sum(E... source) {
        return 1;
    }
}
