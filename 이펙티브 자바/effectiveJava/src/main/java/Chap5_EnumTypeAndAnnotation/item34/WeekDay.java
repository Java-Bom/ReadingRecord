package Chap5_EnumTypeAndAnnotation.item34;

public enum WeekDay {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SATURDAY(5),
    SUNDAY(6);


    private final int value;
    private int var;

    WeekDay(int value) {
        this.value = value;
        this.var = 1;
    }

    public int getValue() {
        return value;
    }

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }

    public void printHi() {
        System.out.println("Hi! This is " + this.name());
    }

}
