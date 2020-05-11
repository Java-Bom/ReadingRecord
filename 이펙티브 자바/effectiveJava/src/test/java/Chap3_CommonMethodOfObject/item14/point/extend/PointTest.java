package Chap3_CommonMethodOfObject.item14.point.extend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PointTest {

    @DisplayName("올바른 다운 캐스팅일때는 일반 규약을 지키며 동작하는 것 처럼 보인다.")
    @Test
    void test() {
        Point point = new ColorPoint(1, 2, 4);
        ColorPoint colorPoint = new ColorPoint(1, 2, 3);

        assertThat(point.compareTo(colorPoint)).isEqualTo(1);
        assertThat(colorPoint.compareTo(point)).isEqualTo(-1);
    }

    @DisplayName("잘못된 다운 캐스팅으로 ClassCastException 이 발생한다.")
    @Test
    void test1() {
        Point point = new Point(1, 2);
        ColorPoint colorPoint = new ColorPoint(1, 2, 3);

        assertThat(point.compareTo(colorPoint)).isEqualTo(0);
        assertThatThrownBy(() -> colorPoint.compareTo(point))
                .isInstanceOf(ClassCastException.class);
    }

}
