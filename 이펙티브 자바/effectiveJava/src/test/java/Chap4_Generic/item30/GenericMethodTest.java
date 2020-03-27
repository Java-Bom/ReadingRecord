package Chap4_Generic.item30;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GenericMethodTest {

    @DisplayName("단순한 제네릭 메서드 사용법")
    @Test
    void generic() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        assertThat(GenericMethod.union(list1, list2)).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));

        List<String> list3 = Arrays.asList("str1", "str2");
//        assertThat(GenericMethod.union(list1, list3)).isEqualTo(Arrays.asList(1,2,3,4,5,6)); compileError
    }

}
