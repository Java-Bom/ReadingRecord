package Chap5_EnumTypeAndAnnotation.item34;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operation {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private static final Map<String, Operation> s = new HashMap<>();
    private static final int VALUE = 1;
    private static final Map<String, Operation> stringToEnum =
            Stream.of(Operation.values())
                    .collect(Collectors.toMap(Operation::toString, operation -> operation));
    private final String symbol;
    private final Map<String, Operation> test = new HashMap<>();

//    static {
//        for (Operation operation : values()) {
//            test.put(operation.symbol,operation);
//        }
//        System.out.println("static 필드");
//    }

    Operation(String symbol) {
        this.symbol = symbol;
        putString(symbol, this);
    }

    public static Operation fromString2(String symbol) {
        return s.get(symbol);
    }

    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    public static Operation inverse(Operation operation) {
        switch (operation) {
            case PLUS:
                return Operation.MINUS;
            case MINUS:
                return Operation.PLUS;
            case TIMES:
                return Operation.DIVDE;
            case DIVDE:
                return Operation.TIMES;
        }
        throw new AssertionError("알 수 없는 연산 : " + operation);
    }

    @Override
    public String toString() {
        return symbol;
    }

    public void putString(String symbol, Operation operation) {
        stringToEnum.put(symbol, operation);
    }

    public abstract double apply(double x, double y);
}
