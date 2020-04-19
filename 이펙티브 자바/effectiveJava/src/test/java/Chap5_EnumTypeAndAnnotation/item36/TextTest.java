package Chap5_EnumTypeAndAnnotation.item36;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TextTest {
    @DisplayName("Collections의 unmodifiableSet 메소드를 이용하여 불변 EnumSet을 만들 수 있다.")
    @Test
    void name() {
        Set unmodifiableSet = Collections.unmodifiableSet(EnumSet.of(Text.Style.BOLD, Text.Style.UNDERLINE));
        assertThatThrownBy(() -> unmodifiableSet.add(Text.Style.ITALIC))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}