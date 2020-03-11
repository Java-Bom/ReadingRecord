package item13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SubTest {

    @Test
    @DisplayName("재정의 가능한 메소드를 상위 클래스의 clone에서 호출하면 하위 클래스의 메소드가 실행된다.")
    void testClone() {
        Sub sub = new Sub();
        assertAll(
                () -> assertThat(sub.type).isEqualTo("super"),
                () -> {
                    Sub clone = sub.clone();
                    assertThat(sub.type).isEqualTo("sub");
                }
        );
    }
}