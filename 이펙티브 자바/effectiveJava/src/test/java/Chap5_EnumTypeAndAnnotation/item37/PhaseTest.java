package Chap5_EnumTypeAndAnnotation.item37;

import org.junit.jupiter.api.Test;

class PhaseTest {

    @Test
    void name() {
        System.out.println(Phase.Transition.getTransitionMap());
    }
}