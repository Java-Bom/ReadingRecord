package Chap6_LambdaAndStream.item42;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationTest {
    PLUS("+") {
        public double apply(double x, double y) {
            System.out.println(symbol);
            return x + y;
        }
    };

    protected final String symbol;

    public abstract double apply(double x, double y);
}
