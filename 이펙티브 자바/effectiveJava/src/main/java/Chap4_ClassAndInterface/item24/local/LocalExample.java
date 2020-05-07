package Chap4_ClassAndInterface.item24.local;

public class LocalExample {
    private int number;

    public LocalExample(int number) {
        this.number = number;
    }

    public void foo() {
        // 지역변수처럼 선언해서 사용할 수 있다.
        class LocalClass {
            private String name;

            public LocalClass(String name) {
                this.name = name;
            }

            public void print() {
                // 비정적 문맥에선 바깥 인스턴스를 참조 할 수 있다.
                System.out.println(number + name);
            }

        }

        LocalClass localClass = new LocalClass("local");

        localClass.print();
    }
}
