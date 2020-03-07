package item24;

interface Operator {
    double plus();

    double minus();
}

public class AnonymousExample {
    double x;
    double y;

    public AnonymousExample(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //정적인 구문
    public static void staticMethod() {
        Operator operator = new Operator() {
            private static final String foo = "foo";

            //            private static String cant = "cant";
            @Override
            public double plus() {
//                return x;
                return 0;
            }

            @Override
            public double minus() {
                return 0;
            }
        };
    }

    //비 정적인 구문 멤버변수의 참조가 가능
    public double nonStaticMethod() {
        Operator operator = new Operator() {
            private static final String foo = "foo";

            @Override
            public double plus() {
                return x + y;
            }

            @Override
            public double minus() {
                return x - y;
            }
        };

        return operator.plus();
    }
}
