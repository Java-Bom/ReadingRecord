package Chap6_EnumTypeAndAnnotation.item37;

public class CustomContainer<K extends Enum<K>> {
    public K[] universe;

    public CustomContainer(K... hi) {
        this.universe = hi;
    }

    public void t(Enum... a) {
        universe[0] = (K) a[0];
        universe[1] = (K) a[1];
    }

    public void hi() {
        System.out.println(universe);
    }
}
