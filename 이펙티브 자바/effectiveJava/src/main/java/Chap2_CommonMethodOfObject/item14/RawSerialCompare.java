package Chap2_CommonMethodOfObject.item14;

public class RawSerialCompare implements Comparable<RawSerialCompare> {

    private long height;
    private double weight;
    private int age;

    public RawSerialCompare(long height, double weight, int age) {
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    @Override
    public int compareTo(RawSerialCompare rawSerialCompare) {
        int result = Long.compare(height, rawSerialCompare.height);
        if (result == 0) {
            result = Double.compare(weight, rawSerialCompare.weight);
            if (result == 0) {
                return Integer.compare(age, rawSerialCompare.age);
            }
        }
        return result;
    }
}
