package Chap3_ClassAndInterface.item19;

public class Super {
    public Super() {
//        overrideMe();
        helpMethod();
    }

    public void overrideMe() {
        helpMethod();
    }

    private void helpMethod() {
        System.out.println("super method");
    }

}
