package Chap5_EnumTypeAndAnnotation.item38;

public class Apple {
    public static final Apple PIE = new Apple("Apple Pie");
    public static final Apple STEW = new Apple("Apple Stew");
    public static final Apple JAM = new Apple("Apple Jam");
    private final String type;

    private Apple(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
