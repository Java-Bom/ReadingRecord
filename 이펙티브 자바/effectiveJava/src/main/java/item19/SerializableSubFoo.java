package item19;

public class SerializableSubFoo extends SerializableFoo {
    String str;

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public void overrideMe() {
        System.out.println("This is SubFoo's overrideMe");
        if (str == null) {
            throw new NullPointerException();
        }
        System.out.println(str);
    }
}
