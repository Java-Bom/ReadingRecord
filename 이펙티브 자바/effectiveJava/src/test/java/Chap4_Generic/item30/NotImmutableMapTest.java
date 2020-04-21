package Chap4_Generic.item30;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotImmutableMapTest {

    @DisplayName("불변이 아닌 클래스를 제네릭싱글턴팩터리로 제공하면 안된다.")
    @Test
    void generic() {
        NotImmutableMap integerNotImmutableMap = NotImmutableMap.getSingleton();

        integerNotImmutableMap.put("key", "123");
        integerNotImmutableMap.put(123, 123);

        assertThat(integerNotImmutableMap.get("key")).isEqualTo("123");
        int maybeInteger = (Integer) integerNotImmutableMap.get("key"); // castException
    }

}
