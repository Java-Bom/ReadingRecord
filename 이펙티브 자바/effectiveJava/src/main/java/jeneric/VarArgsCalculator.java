package jeneric;

import lombok.Getter;


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
