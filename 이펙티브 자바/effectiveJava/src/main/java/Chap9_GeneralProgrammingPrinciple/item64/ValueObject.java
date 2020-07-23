package Chap9_GeneralProgrammingPrinciple.item64;

import Chap9_GeneralProgrammingPrinciple.item64.vo.Name;
import Chap9_GeneralProgrammingPrinciple.item64.vo.Player;

public class ValueObject {
    public Player makePlayer() {
        Name name = new Name("Minsu");
        return new Player(name);
    }

    public void number() {
        Number number = 10000000;
        Integer integer = 10000000;
    }
}
