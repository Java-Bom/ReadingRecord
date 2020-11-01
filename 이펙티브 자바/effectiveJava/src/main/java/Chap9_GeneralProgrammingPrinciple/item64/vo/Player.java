package Chap9_GeneralProgrammingPrinciple.item64.vo;

public class Player {
    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public String getName() {
        return name.getName();
    }
}
