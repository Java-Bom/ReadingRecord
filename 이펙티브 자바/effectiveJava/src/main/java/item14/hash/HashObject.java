package item14.hash;

import java.util.Objects;

public class HashObject implements Comparable<HashObject> {
    private int a;
    private int b;

    public HashObject(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashObject that = (HashObject) o;
        return a == that.a &&
                b == that.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public int compareTo(HashObject hashObject) {
        System.out.println(this.hashCode());
        System.out.println(hashObject.hashCode());

        return Integer.compare(this.hashCode(), hashObject.hashCode());
    }
}
