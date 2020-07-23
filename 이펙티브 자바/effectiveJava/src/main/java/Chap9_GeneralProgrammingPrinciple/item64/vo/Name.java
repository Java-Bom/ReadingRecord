package Chap9_GeneralProgrammingPrinciple.item64.vo;

import java.util.Objects;

public class Name {
    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateName(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("null or empty Name!");
        }
    }
}
