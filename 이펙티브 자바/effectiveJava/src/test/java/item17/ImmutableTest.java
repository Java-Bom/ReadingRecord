package item17;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by jyami on 2020/02/20
 */
class ImmutableTest {

    @Test
    void makeUpperC1() {
        List<String> myList = Arrays.asList("c1", "a2", "b3", "4", "5");
        List<String> strings = new Immutable().makeUpperCWithFunctional(myList);
        assertThat(strings.get(1)).isEqualTo("C1");
    }

    @Test
    void makeUpperC2() {
        List<String> myList = Arrays.asList("c1", "a2", "b3", "4", "5");
        List<String> strings = new Immutable().makeUpperCWithProcedural(myList);
        assertThat(strings.get(1)).isEqualTo("c1");
    }
}