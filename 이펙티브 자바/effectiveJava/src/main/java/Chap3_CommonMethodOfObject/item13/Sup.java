package Chap3_CommonMethodOfObject.item13;

public class Sup implements Cloneable{
    String type;

    public Sup() {
        this.type = "super";
    }

    public void overrideMe() {
        System.out.println("super method");
    }

    @Override
    public Sup clone() {
        try {
            overrideMe();
            return (Sup) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }
}
