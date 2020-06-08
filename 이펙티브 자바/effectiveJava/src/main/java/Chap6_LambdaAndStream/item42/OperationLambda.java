package Chap6_LambdaAndStream.item42;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.DoubleBinaryOperator;

@Getter
@RequiredArgsConstructor
public enum OperationLambda {
    PLUS("+", (x, y) -> {
        //System.out.println(symbol);
        return x + y;
    });

    protected final String symbol;
    private final DoubleBinaryOperator op;

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }
}
