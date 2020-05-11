package Chap6_EnumTypeAndAnnotation.item37;

import Chap6_EnumTypeAndAnnotation.item35.Ensemble;
import org.junit.jupiter.api.Test;

class CustomContainerTest {
    @Test
    void name() {
        CustomContainer<LifeCycle> customContainer = new CustomContainer<>(LifeCycle.ANNUAL, LifeCycle.BIENNIAL);
        customContainer.hi();
        customContainer.t(LifeCycle.PERNNIAL, Ensemble.TRIO);
    }
}
