package Chap5_EnumTypeAndAnnotation.item37;

import Chap5_EnumTypeAndAnnotation.item35.Ensemble;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

class LifeCycleTest {

    @Test
    void name() {
        test(Ensemble.TRIO);

    }

    public <K extends Enum<K>> void test(Enum... enumerate) {
        Map<LifeCycle, String> map = new EnumMap<LifeCycle, String>(LifeCycle.class);

        map.put((LifeCycle) enumerate[0], "hi");
    }
}