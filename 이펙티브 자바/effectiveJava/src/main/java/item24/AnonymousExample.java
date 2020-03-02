package item24;

interface Operator {
    double plus();

    double minus();
}

public class AnonymousExample {
    private double x;
    private double y;

    public AnonymousExample(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double plus() {
        Operator operator = new Operator() {
            private static final String foo = "foo";

            @Override
            public double plus() {
                System.out.printf("%f + %f = %f\n", x, y, x + y);
                return x + y;
            }

            @Override
            public double minus() {
                System.out.printf("%f - %f = %f\n", x, y, x - y);
                return x - y;
            }
        };

        return operator.plus();
    }
}
