package Chap7_LambdaAndStream.item42_44;

import java.util.function.Function;

public class Lambda {
    private String name;
    private Function<String, String> op = (s1) -> {
        System.out.println(this.name);
        return s1;
    };


    class ac {
        public void a() {
            Function<String, String> op = (s1) -> {
                return s1;
            };
        }
    }
}
