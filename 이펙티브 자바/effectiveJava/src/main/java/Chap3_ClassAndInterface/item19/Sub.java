package Chap3_ClassAndInterface.item19;

public class Sub extends Super{
    private String str;
    public Sub() {
        str = "Sub String";
    }

    @Override
    public void overrideMe() {
        System.out.println(str);
    }


    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
